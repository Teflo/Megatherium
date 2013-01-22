Ext.define('Megatherium.communicator.MegatheriumCommunicator', {
	SERVER_URL: 'http://megatherium.org:8084/LordsAndKnights_Server/',
	request: {
		callback: null,
		scope: null,
		servlet: ''
	},
	
	/**
	 * Tries to login the user.
	 * 
	 * @param login the login data, either the name or the email of the user
	 * @param password the password
	 * @param callback the callback function after the request has been loaded; [1] response object [2] boolean login success
	 * @param scope the scope within that the callback function will be called
	 */
	login: function(login, password, callback, scope) {
		var request = this.createRequest('UserLoginServlet', callback, scope);
		request.set("login", login).set("password", password).execute();
	},
	
	/**
	 * Tries to create a new user.
	 * 
	 * @param name the name of the user
	 * @param email the email adress of the user
	 * @param password the user's password (encrypted)
	 * @param callback the callback function receiving the response aso
	 * @param scope the scope within that the callback function will be executed
	 */
	createUser: function(name, email, password, callback, scope) {
		var request = this.createRequest('UserCreateServlet', callback, scope);
		request.set("name", name).set("email", email).set("password", password).execute();
	},
	
	/**
	 * Loads the list of platforms and returns it.
	 * 
	 * @param callback the callback function
	 * @param scope the scope within that the callback function will be executed
	 */
	getPlatformList: function(callback, scope) {
		var request = this.createRequest('PlatformListServlet', callback, scope);
		request.execute();
	},
	
	/**
	 * This method creates a new http request with some predefined configs and the callback method.
	 * 
	 * @param servlet the name of the servlet at the megatherium server that will be called
	 * @param callback the callback method
	 * @param scope the scope within that the callback method will be executed
	 */
	createRequest: function(servlet, callback, scope) {
		// save request data
		this.request = {
			servlet: servlet,
			callback: callback,
			scope: scope
		};
		
		// create http request object
		var request = Ext.create('Megatherium.request.HttpRequest', this.SERVER_URL+servlet);
		request.setCallback(function(data) {
			var scope = this.request.scope;
			var callback = this.request.callback;
			scope.callback = callback;
			scope.callback(data, (data.response.status == 'success'));
		}, this);
		return request;
	}
	
});

var MegatheriumCommunicator = Ext.create('Megatherium.communicator.MegatheriumCommunicator');