Ext.onReady(function() {
	var store = new Ext.data.JsonStore({
		root: 'rows',
		totalProperty: 'total',
		idProperty: 'guid',
		remoteSort: false,
		pruneModifiedRecords: true,
		region: 'center',
		fields: [<#list colList as list>'${list['propname']}'<#if list_has_next>,</#if></#list>],
		proxy: new Ext.data.HttpProxy({
			url: '${tablename}!list.action'
		})
	});
	var pagingBar = new Ext.PagingToolbar({
		pageSize: 20,
		store: store,
		displayInfo: true
	});
	var fm = Ext.form;
	var sm = new Ext.grid.CheckboxSelectionModel();
	var grid = new Ext.grid.EditorGridPanel({
		id: 'grid',
		region: 'center',
		title: '请修改这里的标题内容',
		store: store,
		margins: '5 5 5 5',
		stripeRows: true,
		sm: sm,
		clicksToEdit: 1,
		loadMask: true,
		viewConfig: {
			forceFit: true
		},
		columns: [
		sm,<#list colList as list>{
			header: "${list['propname']}",
			dataIndex: '${list['propname']}',
			editor: new fm.TextField({
				allowBlank: false
			})
		}<#if list_has_next>,</#if></#list>],
		bbar: pagingBar,
		tbar: [{
			text: '新增',
			iconCls: 'add',
			handler: function() {
				var entity = new Ext.data.Record({
					<#list colList as list>
					${list['propname']}: ""<#if list_has_next>,</#if>
					</#list>
				});
				grid.stopEditing();
				store.insert(0, entity);
				grid.startEditing(0, 1);
			}
		}, {
			text: '保存',
			iconCls: 'accept',
			handler: function() {
				var mod = store.getModifiedRecords();
				updateData(mod);
			}
		}, {
			text: '删除',
			iconCls: 'delete',
			handler: function() {
				var mod = sm.getSelections();
				removeData(mod);
			}
		}]
	});
	store.load({
		params: {
			start: 0,
			limit: 20
		}
	});

	var viewport = new Ext.Viewport({
		layout: 'border',
		items: [grid]
	});
});
/**
 * 更改操作
 * @param  {[type]} mod [description]
 * @return {[type]}     [description]
 */

function updateData(mod) {
	if (mod.length == 0) {
		msg('请确认您已选择了需要操作的数据！');
		return;
	}
	var json = [];
	Ext.each(mod, function(item) {
		json.push(item.data);
	});
	var tmp = Ext.util.JSON.encode(json);
	var array = {
		json: tmp
	};
	_post("${tablename}!update.action", array, function() {
		reload();
	});
}
/**
 * 删除数据
 * @param  {[type]} mod [description]
 * @return {[type]}     [description]
 */

function removeData(mod) {
	if (mod.length == 0) {
		msg('请确认您已选择了需要删除的数据！');
		return;
	}
	var array = [];
	Ext.each(mod, function(node) {
		array.push(node['data']['guid']);
	});
	_post("${tablename}!del.action", {
		guid: array.join(',')
	}, function() {
		reload();
	});
}
/**
 * 刷新数据
 * @return {[type]} [description]
 */

function reload() {
	//表格刷新
	var grid = Ext.getCmp('grid');
	grid.getStore().reload();
}