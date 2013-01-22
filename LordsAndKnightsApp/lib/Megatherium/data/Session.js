Ext.define('SessionClass', {
	userID: 0,
	accountList: [],
	onUpdateListeners: [],
	
	addOnUpdateListener: function(listener, scope) {this.onUpdateListeners.push({fn: listener, scope: scope}); return this;},
	setAccountList: function(accountList) {this.accountList = accountList; return this;},
	getAccountList: function() {return this.accountList;},
	getUserID: function() {return this.userID;},
	setUserID: function(userID) {this.userID = userID; return this;},
	isLoggedIn: function() {return (this.userID > 0);},
	
	/**
	 * Updates the js session object with data from the server.
	 */
	update: function(data) {
		this.setUserID(data.userID);
		this.setAccountList(data.accountList);
		
		// call listeners
		Ext.Array.each(this.onUpdateListeners, function(listener) {
			var scope = listener.scope;
			var fn = listener.fn;
			scope[fn]();
		});
	}
});

var Session = Ext.create('SessionClass');