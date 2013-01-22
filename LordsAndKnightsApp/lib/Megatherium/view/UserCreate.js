Ext.define('Megatherium.view.UserCreate', {
    extend: 'Ext.form.Panel',
	alias: 'widget.usercreateview',
	requires: ['Ext.field.Email', 'Ext.field.Text', 'Ext.field.Password', 'Ext.TitleBar'],

    config: {
		scrollable: 'vertical',
        id: 'userCreateForm',
        items: [
			{
				xtype: 'titlebar',
				title: 'Registrieren',
				docked: 'top'
			},
            {
                xtype: 'textfield',
                label: 'Benutzername',
				labelAlign: 'top',
				name: 'name'
            },
            {
                xtype: 'emailfield',
                label: 'E-Mail',
				labelAlign: 'top',
                placeHolder: 'email@example.com',
				name: 'email'
            },
            {
                xtype: 'passwordfield',
                label: 'Passwort',
				labelAlign: 'top',
				name: 'password'
            },
            {
                xtype: 'button',
                ui: 'confirm',
				itemId: 'submit',
                text: 'Registrieren',
				margin: '10 0 0 0'
            },
            {
                xtype: 'button',
                itemId: 'cancel',
                ui: 'action',
                text: 'Abbrechen'
            }
        ],
        listeners: [
            {
                fn: 'onCancel',
                event: 'tap',
                delegate: '#cancel'
            },
			{
				fn: 'onSubmit',
				event: 'tap', 
				delegate: '#submit'
			}
        ]
    },
	
	onCancel: function(button, e, options) {
		this.fireEvent('switchToUserLogin', this);
	},
	
	onSubmit: function(button, e, options) {
		this.fireEvent('performUserCreate', this);
	}

});