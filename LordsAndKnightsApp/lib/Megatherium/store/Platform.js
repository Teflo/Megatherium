Ext.define('Megatherium.store.Platform', {
	extend: 'Ext.data.Store',
	
	config: {
		autoLoad: false,
		model: 'Megatherium.model.Platform',
		storeId: 'platformStore',
		sorters: [
			{property: 'label', direction: 'ASC'}
		]
	},
	
	initialize: function(arguments) {
		this.callParent(arguments);
		this.loadPlatformList();
	},
	
	loadPlatformList: function() {
		Ext.data.JsonP.request({
			url: Config.SERVER_URL+'PlatformListServlet',
			scope: this,
			success: function(data) {
				Ext.getStore("platformStore").add(data.response.data);
			}
		});
	},
	
	/**
	 * Returns the label of the platform.
	 * 
	 * @param name the name of the platform
	 * @return the label of the platform or null
	 */
	getLabel: function(name) {
		return this.findRecord('name', name).get("label");
	}
	
});