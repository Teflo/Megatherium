//<debug>
Ext.Loader.setPath({
    'Ext': 'lib/sencha/src'
});

Ext.Loader.setPath({
    'SliderMenu': 'lib/slider_menu',
	'Megatherium': 'lib/Megatherium'
});
//</debug>

Ext.application({
	name: 'LordsAndKnights',
	
	// load views
	views: [
		'LordsAndKnights.view.Home',
		'LordsAndKnights.view.SlideNavigation',
		'Megatherium.view.AccountList',
		'Megatherium.view.UserCreate',
		'Megatherium.view.UserLogin'
	],
	
	requires: ['Ext.MessageBox'],
	
	controllers: [
		'Main',
		'SliderMenu.controller.SliderMenuController'
	],
	
	stores: [
		'Megatherium.store.Account',
		'Megatherium.store.Platform'
	],
	
	models: [
		'Megatherium.model.Account',
		'Megatherium.model.Platform'
	],
	
	phoneStartupScreen: 'resources/loading/homescreen.jpg',
	tabletStartupScreen: 'resources/loading/homescreenTablet.jpg',
	
	launch: function() {
		Ext.Viewport.add([{xtype: 'userloginview'}]);
//		Ext.data.JsonP.request({
//			scope: this,
//			url: 'http://spinshare.de:8080/Server/HomeServlet',
//			success: function(data) {
//				// update platform list
//				Ext.getStore("platformStore").add(data.response.data.platformList);
//				
//				// save in session aso
//				proceed(data);
//				
//				// display views
//				if (Session.isLoggedIn()) {
//					Ext.Viewport.add([{xtype: 'slidenavigation'}, {xtype: 'conversationview'}]);
//				}else {
//					Ext.Viewport.add([{xtype: 'loginformview'}, {xtype: 'slidenavigation'}, {xtype: 'conversationview'}]);
//				}
//			}
//		});
	}
});