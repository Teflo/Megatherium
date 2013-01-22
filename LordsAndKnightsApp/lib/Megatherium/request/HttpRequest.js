Ext.define('Megatherium.request.HttpRequest', {
	url: '',
	parameters: {},
	response: null,
	callback: {
		fn: null,
		scope: null
	},
	
	/**
	 * Initializes the http request object.
	 * 
	 * @param url the url that will be called
	 */
	constructor: function(url) {
		this.url = url;
	},
	
	/**
	 * Sets the callback message that will receive the server response.
	 * 
	 * @param callback the callback function
	 * @param scope the scope within that the callback function will be executed (class must not contain the method "callback")
	 */
	setCallback: function(callback, scope) {
		this.callback.fn = callback;
		this.callback.scope = scope;
	},
	
	/**
	 * Adds another parameter that will be added to the request.
	 * 
	 * @param name the name
	 * @param value the value
	 * @return the class instance itself for faster programming
	 */
	set: function(name, value) {
		this.parameters[name] = value;
		return this;
	},
	
	/**
	 * Executes the request.
	 * 
	 * @param callback the callback function
	 * @param scope the scope within that the callback method will be executed
	 */
	execute: function(callback, scope) {
		if (scope == undefined) scope = this.callback.scope;
		if (callback == undefined) scope.callback = this.callback.fn;
		Ext.data.JsonP.request({
			url: this.url,
			scope: this,
			params: this.parameters,
			success: function(response) {
				this.response = response;
				scope.callback(response);
			}
		});
	}
	
});