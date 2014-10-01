$(document).ready(
		function() {
			$('#calendar-container').datepicker(
					{
						numberOfMonths : 3,
						onSelect : function() {
							/*
							 * the jQuery UI code sets the width on the element,
							 * we need to remove this any time jQuery tries to
							 * reset it
							 */
							$('#calendar-container .ui-datepicker-inline').css(
									'width', '');
						}
					});

			/* I like the rounded corners on the calendar headers so... */
			$('#calendar-container .ui-datepicker-header').removeClass(
					'ui-corner-right').removeClass('ui-corner-left');
			$('#calendar-container .ui-datepicker-header').addClass(
					'ui-corner-right').addClass('ui-corner-left');
		});