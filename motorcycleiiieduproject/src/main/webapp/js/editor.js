import Editor from '@ckeditor/ckeditor5-core/src/editor/editor';
import EditorUIView from '@ckeditor/ckeditor5-ui/src/editorui/editoruiview';
import InlineEditor from '@ckeditor/ckeditor5-build-inline';
var num = $('.edit').length;

$(document).ready(function(){
	$.ajax({
		type: "get",
		url: "queryAllWebContent",
		contentType: "application/json; charset=utf-8",
		success: function (response) {
// 			alert(response[0].webElements+ +response[0].webContent);
			for(i=0; i<num; i++){ $('#'+response[i].webElements).empty().append(response[i].webContent); }
		},
	     error:function(responseerror){
	         alert(responseerror.responseText)
	     }
	});
});	

//	for(i=1; i<=num; i++){
//	$("#editor"+i).on('click',function(){
//		alert(this.id);
//		InlineEditor
//     .create( document.querySelector( '#editor'+i ) )
//     .then( newEditor => {
//		   this.id = newEditor;
//		})
//		.catch( error => {
//		   console.error( error );
//		});
//	});
//}

//	for(i=0; i<num; i++){
//	editor = "editor"+i;
// InlineEditor.create(document.querySelector('#editor'+i))
//     .then( newEditor => {
//     	editor.val() = newEditor;
//		})
//		.catch( error => {
//		   console.error( error );
//	});
//}



//$("#editor1").on('click',function(){
	InlineEditor
    .create( document.querySelector( '#editor1' ) )
    .then( newEditor => {
	   editor1 = newEditor;
	})
	.catch( error => {
	   console.error( error );
	});
//});

//$("#editor2").on('click',function(){	
    InlineEditor
        .create( document.querySelector( '#editor2' ) )
        .then( newEditor => {
		   editor2 = newEditor;
		})
		.catch( error => {
		   console.error( error );
		});
//});

//$("#editor3").on('click',function(){
    InlineEditor
        .create( document.querySelector( '#editor3' ) )
        .then( newEditor => {
		   editor3 = newEditor;
		})
		.catch( error => {
		   console.error( error );
		});
//});

InlineEditor
	.create( document.querySelector( '#editor4' ) )
	.catch( error => {
		console.error( error );
	});
InlineEditor
	.create( document.querySelector( '#editor5' ) )
	.catch( error => {
		console.error( error );
	});
InlineEditor
	.create( document.querySelector( '#editor6' ) )
	.catch( error => {
		console.error( error );
	});
InlineEditor
	.create( document.querySelector( '#editor7' ) )
	.catch( error => {
		console.error( error );
	});