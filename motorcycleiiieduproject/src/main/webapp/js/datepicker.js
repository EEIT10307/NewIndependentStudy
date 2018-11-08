var monthsen =["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var months = [];
months['en'] = monthsen;
var loadcalendar = function() {

$('#demo').daterangepicker({

     "timePicker": true,
     "timePicker24Hour": true,
     "timePickerIncrement": 15,
     "autoApply": true,
     "dateLimit": {"days": 30},
     "locale": {
       "format": "YYYY-MM-DD hh:mm",
       "separator": " - ",
       "applyLabel": "Filtrer",
       "cancelLabel": "Annuler",
       "fromLabel": "Du",
       "toLabel": "au",
       "customRangeLabel": "Custom",
       "weekLabel": "W",
       "daysOfWeek": [
         "Su",
         "Mo",
         "Tu",
         "We",
         "Th",
         "Fr",
         "Sa"
       ],
       "monthNames": months[$('#lang').val()],
       "firstDay": 1
     },
     "alwaysShowCalendars": true,
     "startDate": new Date(),
     "endDate": new Date(),
     "minDate":"1999-12-12",
     "maxDate":"2050-12-30",
     "opens": "center",
     "timePicker": true,
     "timePickerIncrement": 1,
     "applyClass": "btn_primary icon-valid",
     "cancelClass": "btn_light icon-close"
   },
function(start, end, label) {
     console.log('選擇日期範圍: ' + start.format('YYYY-MM-DD hh:mm') + ' 到 ' + end.format('YYYY-MM-DD hh:mm') + '(predefined range: ' + label + ')');
   });
}

loadcalendar();