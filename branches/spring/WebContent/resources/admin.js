

function ajax(obj, suc_func){

	$.ajax({
		  dataType:'json',
		  url: '../ajax.html',
		  data: obj,
		  success: function(data){
			  if(!data.error){
				  suc_func(data);
			  }
			  else{
				  document.write(object.error);
			  }
		  }
	});

}