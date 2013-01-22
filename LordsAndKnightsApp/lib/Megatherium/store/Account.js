Ext.define('Megatherium.store.Account', {
	extend: 'Ext.data.Store',
	
	config: {
		autoLoad: false,
		model: 'Megatherium.model.Account',
		storeId: 'accountStore',
		sorters: [
			{property: 'alias', direction: 'ASC'}
		],
		grouper: {
			groupFn: function(record) {
				return Ext.getStore("platformStore").getLabel(record.get('platformName'));
			}
		}
	},
	
	initialize: function(arguments) {
		this.callParent(arguments);
		this.loadAccountList();
	},
	
	loadAccountList: function() {
		Ext.data.JsonP.request({
			url: Config.SERVER_URL+'AccountListServlet',
			scope: this,
			success: function(data) {
				Ext.getStore("accountStore").add(data.response.data);
			}
		});
	}
	
});