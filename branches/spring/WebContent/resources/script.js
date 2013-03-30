
$(function(){
	
	$('select.departments-combo').change(function(){
		var depid = $(this).children('select option:selected').val();
		

		getGroups(depid);


		$('select.groups-combo').change();
	});
		
	$('select.departments-combo').filter('.fire-change-on-ready').change();
});

function getGroups(depId){
	var obj = {};
	obj.cmd = 'get_groups';
	obj.depid = depId;
	
	
	ajax(obj, function(object){
		$('select.groups-combo').each(function(){
			$(this).html('');
			
			
			for(var i = 0; i<object.data.length; i++){
				var el = object.data[i];

				addOption($(this), el.id, el.name);
			}
			
		});
	});
	
}

function addOption($select, val, html){
	var option = $(document.createElement('option'));
	option.val(val);
	option.html(html);
	$select.append(option);
}


