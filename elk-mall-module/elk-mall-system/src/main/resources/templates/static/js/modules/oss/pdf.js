$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'oss/pdf/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 20, key: true ,hidden:true},
			{ label: '文件名', name: 'name', width: 50},
			{ label: '大小', name: 'size', width: 30},
            { label: 'URL地址', name: 'url', width: 200 ,formatter:function(value,row,index) {
				return '<a href=\''+value+'\' target=\'_blank\'>'+value+'</a>';
			}},
			 { label: '操作', name: 'type', width: 40 ,formatter:function(value,row,index) {
				 	if(index.img!=null){
				 		return '<a href=\''+'../../static/plugins/pdf/web/viewer.html?file='+index.pdf+'\' target=\'_blank\'>'+"预览"+'</a><a href=\''+index.img+'\' target=\'_blank\'>'+"|下载图片"+'</a>';
				 	}else{
				 		return '<a href=\''+'../../static/plugins/pdf/web/viewer.html?file='+index.pdf+'\' target=\'_blank\'>'+"预览"+'</a>';
				 	}
					
					/*return '<a href=\''+'../../pdf/web/viewer.html'+'\' target=\'_parent\'>'+"预览"+'</a>';*/
				}},
			{ label: '创建者', name: 'creator', width: 20 },
			{ label: '创建时间', name: 'createDate', width: 50 }
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

    new AjaxUpload('#upload', {
        action: baseURL + 'oss/pdf/upload?token=' + token,
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if(vm.config.type == null){
                alert("云存储配置未配置");
                return false;
            }
            if (!(extension && /^(doc|docx|xls|xlsx|ppt|pptx|png|svg|rtf)$/.test(extension.toLowerCase()))){
                alert('只支持doc,docx,xls,xlsx,ppt,pptx,png,svg,rtf格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
        	 if(r.code == 200){
                 alert('上传成功', function(){
                     vm.reload();
                 });
             }else{
                 alert(r.msg);
             }
        }
    });

});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		water: {},
		dicSelect:{},
        config: {}
	},
    created: function(){
        this.getConfig();
    },
	methods: {
		query: function () {
			vm.reload();
		},
		 getDic: function () {//下拉选项字典查询
			 $.get(baseURL + "sys/dic/query/"+"water", function(r){
			        vm.dicSelect = r.data;
			   });
		},
		upload: function(){
	            vm.showList = false;
	            vm.title = "上传office文档";           
	        },
		getConfig: function () {
            $.getJSON(baseURL + "oss/file/config", function(r){
				vm.config = r.data;
            });
        },
        getWater: function () {
            $.getJSON(baseURL + "oss/pdf/water", function(r){
            	if(r.data==null){
            		  vm.water = {type:1,enable:0};
            	}else{
            		vm.water = r.data;
            	}
				
            });
        },
		setWater: function(){
			vm.showList = false;
			vm.title = "水印配置";
			vm.getDic();
			vm.getWater();
		},
		saveOrUpdate: function () {
			var url = baseURL + "oss/pdf/waterSetting";
			$.ajax({
				type: "POST",
			    url: url,
                contentType: "application/json",
			    data: JSON.stringify(vm.water),
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
		
        del: function () {
            var ossIds = getSelectedRows();
            if(ossIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "oss/pdf/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ossIds),
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
            });
        },
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});