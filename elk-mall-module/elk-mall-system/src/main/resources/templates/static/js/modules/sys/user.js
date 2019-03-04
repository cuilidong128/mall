$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'userId',  width: 45, key: true },
			{ label: '用户名', name: 'username', width: 75 },
            { label: '所属部门', name: 'deptName', width: 75 },
            { label: '职位', name: 'position', width: 50 },
			{ label: '邮箱', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 80 },
			{ label: '状态', name: 'status', width: 40, formatter: function(value, options, row){
				return value === false ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'createTime', width: 85}/*,
			{ label: '操作', name: '', width: 40, formatter: function(value, options, row){
				return 	'<a v-if="hasPermission(\'sys:user:update\')" class="btn btn-primary" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>' ;
					
						
			}}*/
			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            username: null
        },
        showList: true,
        title:null,
        reqId:null,
        roleList:{},
        position: '',
        dicSelect:{},
        currentUserId:null,
        user:{
        	userId:null,
        	status:1,
            deptId:null,
            deptName:null,
            roleIdList:[]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.roleList = {};
            vm.user = {deptName:null, deptId:null, status:1, roleIdList:[]};

            //获取角色信息
            this.getRoleList();
            vm.getDic();
            vm.getDept();
            $.get(baseURL + "sys/gen/reqId", function(r){
				vm.reqId = r.data;
			});
        },
        getDic: function () {//下拉选项字典查询
			 $.get(baseURL + "sys/dic/query/"+"position", function(r){
			        vm.dicSelect = r.data;
			   });
		},
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if(node != null){
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
                }
            })
        },
        update: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }
        //    debugger
            vm.showList = false;
            vm.title = "修改";
            //清空上次权限数据
            vm.roleList={};
            //获取当前用户、当前用户角色不能修改
            vm.getCurrentUser(userId);
            vm.getUser(userId);
            //获取角色信息  
            
            vm.getDic();
        },
        del: function () {
            var userIds = getSelectedRows();
            if(userIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function(r){
                        if(r.code == 200){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                headers: {
			        "reqId": vm.reqId
			    },
                contentType: "application/json",
                data: JSON.stringify(vm.user),
                success: function(r){
                    if(r.code === 200){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        getCurrentUser: function(userId){
			$.getJSON(baseURL + "sys/user/info", function(r){
			//	debugger
				vm.currentUserId = r.data.userId;
				  if(parseInt(userId) != r.data.userId){	
	            	  vm.getRoleList();
	            }
			});
		},
        getUser: function(userId){
            $.get(baseURL + "sys/user/info/"+userId, function(r){
                vm.user = r.data;
                vm.position = r.data.position;
                vm.user.password = null;
           //     debugger
                vm.getDept();
            });
        },
        getRoleList: function(){
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.data;
            });
        },
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            //范围查询
            var createTime = {"begin":"2018-03-11","end":"2018-05-12"}
            //多列模糊查询
			var keyParam = new Array();		
			keyParam.push('username');
			keyParam.push('email');
			keyParam.push('mobile');
			var jsonString = JSON.stringify(keyParam);
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'searchKey': vm.q.key,'keyParam':jsonString},
			/*	postData:{'searchKey': vm.q.key,'keyParam':jsonString,'createTime':JSON.stringify(createTime),'username':vm.q.username},*/
                page:page
            }).trigger("reloadGrid");
        }
    }
});