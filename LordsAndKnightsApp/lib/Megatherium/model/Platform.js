Ext.define('Megatherium.model.Platform', {
	extend: 'Ext.data.Model',
	
	config: {
		idProperty: 'name',
		fields: [
			{name: 'name', type: 'string'},
			{name: 'label', type: 'string'}
		]
	}
});