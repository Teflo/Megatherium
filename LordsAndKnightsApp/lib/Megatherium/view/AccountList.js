Ext.define('Megatherium.view.AccountList', {
	extend: 'Ext.List',
	alias: 'widget.accountlistview',
	
	config: {
//		fullscreen: true,
		itemId: 'accountList',
		store: 'accountStore',
		itemTpl: '{alias}'
	//	grouped: true
	}
	
//	initialize: function() {
//		this.callParent(arguments);
//		
//		// add refresh listener to store
//		this.getStore().addListener('addrecords', function() {
//			this.refresh();
//		}, this);
//	}
	
});