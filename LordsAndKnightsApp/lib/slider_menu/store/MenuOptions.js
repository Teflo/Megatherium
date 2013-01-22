/**
 * @class SliderMenu.store.MenuOptions
 * @extends Ext.data.Store
 *
 * Defines store of MenuOptions that are showed inside SliderMenu.view.Menu.
 * It must be modified to adapt SliderMenu at your app.
 * By default, the data is hardcored inside class, but you can get it using a 
 * proxy (Ajax, Rest, LocalStorage...).
 *
 * @author Xevi Gallego (wozznik@gmail.com) (@wozznik) 
 */
Ext.define('SliderMenu.store.MenuOptions', {
    extend: 'Ext.data.Store',
	
    requires: [,
    ],
	
    config: {
    	model: 'SliderMenu.model.MenuOption',
		storeId: 'MenuOptionsStore',
		
		//Customize your menu options
		data: [
			{id: 1, view: 'MegatheriumProject.view.MessageContainerList', iconCls: 'action', iconMask:true, text:'Nachrichten'}
		]
    }
});