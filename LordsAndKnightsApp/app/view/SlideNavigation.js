Ext.define('LordsAndKnights.view.SlideNavigation', {
	extend: 'Ext.ux.slidenavigation.View',
	alias: 'widget.slidenavigationview',
	
	config: {
		title: 'Lords & Knights Bot',
		slideSelector: 'x-toolbar',
		selectSlideDuration: 200,
		 
		list: {
			width: 250,
			maxDrag: null,
			grouped: false
		},
		 
//		items: [
//			{
//				title: 'Abmelden'
//			}
//		]
		items: [
			{
				xtype: 'homeview',
				title: 'Startseite'
			},
			{
				xtype: 'accountlistview',
				title: 'Konten'
			}
		]
	}
});