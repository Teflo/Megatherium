Ext.define('Megatherium.model.Account', {
	extend: 'Ext.data.Model',
	
	config: {
		idProperty: 'id',
		fields: [
			{name: 'id', type: 'int'},
			{name: 'userID', type: 'int'},
			{name: 'platformName', type: 'string'},
			{name: 'loginInformation', type: 'string'},
			{name: 'alias', type: 'string'}
		]
	}
});