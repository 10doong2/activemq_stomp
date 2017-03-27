var amq = org.activemq.Amq;
org.activemq.Chat = function() {
	var last = '';
	var user = null;
	var chatTopic = 'topic://CHAT.DEMO';
	var chat, join, joined, phrase, members, username = null;
	var chatHandler = function(message) {
		var type = message.getAttribute('type');
		var from = message.getAttribute('from');

		switch (type) {
			case 'chat' : {
				var text = message.childNodes[0].data;

				if (from == last) from = '...';
				else {
					last = from;
					from += ':';
				}
				chat.innerHTML += '<span class=\'from\'>' + from + '&nbsp;</span><span class=\'text\'>' + text + '</span><br/>';
				break;
			}
			case 'ping' : {
				members.innerHTML += '<span class="member">' + from + '</span><br/>';
				break;
			}
			case 'join' : {
				members.innerHTML = '';
				if (user != null)
					amq.sendMessage(chatTopic, '<message type="ping" from="' + user + '"/>');
				chat.innerHTML += '<span class="alert"><span class="from">' + from + '&nbsp;</span><span class="text">has joined the room!</span></span><br/>';
				break;
			}

			case 'leave': {
				members.innerHTML = '';
				chat.innerHTML += '<span class="alert"><span class="from">' + from + '&nbsp;</span><span class="text">has left the room!</span></span><br/>';

				if (from == user) {
					join.className = '';
					joined.className = 'hidden';
					username.focus();
					user = null;
					amq.removeListener('chat', chatTopic);
				}
				if (user != null)
					amq.sendMessage(chatTopic, '<message type="ping" from="' + user + '"/>');
				break;
			}
		}

		chat.scrollTop = chat.scrollHeight - chat.clientHeight;
	};

	var getKeyCode = function (ev) {
		var keyc;
		if (window.event) keyc = window.event.keyCode;
		else keyc = ev.keyCode;
		return keyc;
	};

	var addEvent = function(obj, type, fn) {
		if (obj.addEventListener)
			obj.addEventListener(type, fn, false);
		else if (obj.attachEvent) {
			obj["e"+type+fn] = fn;
			obj[type+fn] = function() { obj["e"+type+fn]( window.event ); }
			obj.attachEvent( "on"+type, obj[type+fn] );
		}
	};

	var initEventHandlers = function() {
		addEvent(username, 'keyup', function(ev) {
			var keyc = getKeyCode(ev);
			if (keyc == 13 || keyc == 10) {
				org.activemq.Chat.join();
				return false;
			}
			return true;
		});

		addEvent(document.getElementById('joinB'), 'click', function() {
			org.activemq.Chat.join();
			return true;
		});

		addEvent(phrase, 'keyup', function(ev) {
			var keyc = getKeyCode(ev);

			if (keyc == 13 || keyc == 10) {
				var text = phrase.value;
				phrase.value = '';
				org.activemq.Chat.chat(text);
				return false;
			}
			return true;
		});

		addEvent(document.getElementById('sendB'), 'click', function() {
			var text = phrase.value;
			phrase.value = '';
			org.activemq.Chat.chat(text);
		});

		addEvent(document.getElementById('leaveB'), 'click', function() {
			org.activemq.Chat.leave();
			return false;
		});
	};

	return {
		join: function() {
			var name = username.value;
			if (name == null || name.length == 0) {
				alert('Please enter a username!');
			} else {
				user = name;

				amq.addListener('chat', chatTopic, chatHandler);
				join.className = 'hidden';
				joined.className = '';
				phrase.focus();

				amq.sendMessage(chatTopic, '<message type="join" from="' + user + '"/>');
			}
		},

		leave: function() {
			amq.sendMessage(chatTopic, '<message type="leave" from="' + user + '"/>');
		},

		chat: function(text) {
			if (text != null && text.length > 0) {
				// TODO more encoding?
				text = text.replace('<', '&lt;');
				text = text.replace('>', '&gt;');

				amq.sendMessage(chatTopic, '<message type="chat" from="' + user + '">' + text + '</message>');
			}
		},

		init: function() {
			join = document.getElementById('join');
			joined = document.getElementById('joined');
			chat = document.getElementById('chat');
			members = document.getElementById('members');
			username = document.getElementById('username');
			phrase = document.getElementById('phrase');

			if (join.className == 'hidden' && joined.className == 'hidden') {
				join.className = '';
				joined.className = 'hidden';
				username.focus();
			}

			initEventHandlers();
		}
	}
}();













