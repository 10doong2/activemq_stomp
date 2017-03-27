
var org = org || {};
org.activemq = org.activemq || {};

org.activemq.AmqAdapter = {

	init: function(options) {
	},
	ajax: function(uri, options) {
		request = {
			url: uri,
			data: options.data,
			success: options.success || function(){},
			error: options.error || function(){}
		}
		var headers = {};
		if( options.headers ) {
			headers = options.headers;
		}
		
		if (options.method == 'post') {
			request.type = 'POST';
			headers[ 'Connection' ] = 'close';
		} else {
			request.type = 'GET';
			request.dataType = 'xml';
		}
		
		if( headers ) {
			request.beforeSend = function(xhr) {
				for( h in headers ) {
					xhr.setRequestHeader( h, headers[ h ] );
				}
			}
		}
		
		jQuery.ajax( request );
	},

	log: function(message, exception) {
		if (typeof console != 'undefined' && console.log) console.log(message);
	}
};
