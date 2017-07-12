 function logout_admin(){
  	alert("admin to be logout");
	}
	//////////////////to be converted into one function shortcut later on/////////////////////////////////
function go_admin_home(){
	document.getElementById('admin_home').style.cssText = 'display:block';
	document.getElementById('admin_query').style.cssText = 'display:none';
	document.getElementById('admin_category').style.cssText = 'display:none';
	document.getElementById('admin_subcategory').style.cssText = 'display:none';
	document.getElementById('admin_sme').style.cssText = 'display:none';
	document.getElementById('admin_request').style.cssText = 'display:none';
	}
function go_admin_query(){
	document.getElementById('admin_query').style.cssText = 'display:block';
	document.getElementById('admin_home').style.cssText = 'display:none';
	document.getElementById('admin_category').style.cssText = 'display:none';
	document.getElementById('admin_subcategory').style.cssText = 'display:none';
	document.getElementById('admin_sme').style.cssText = 'display:none';
	document.getElementById('admin_request').style.cssText = 'display:none';
	}
function go_admin_subcategory(){
	document.getElementById('admin_subcategory').style.cssText = 'display:block';
	document.getElementById('admin_home').style.cssText = 'display:none';
	document.getElementById('admin_query').style.cssText = 'display:none';
	document.getElementById('admin_category').style.cssText = 'display:none';
	document.getElementById('admin_sme').style.cssText = 'display:none';
	document.getElementById('admin_request').style.cssText = 'display:none';
	}
function go_admin_sme(){
	document.getElementById('admin_sme').style.cssText = 'display:block';
	document.getElementById('admin_home').style.cssText = 'display:none';
	document.getElementById('admin_query').style.cssText = 'display:none';
	document.getElementById('admin_category').style.cssText = 'display:none';
	document.getElementById('admin_subcategory').style.cssText = 'display:none';
	document.getElementById('admin_request').style.cssText = 'display:none';
	fetch_SMEs_name();	
	}
function go_admin_request(){
	document.getElementById('admin_request').style.cssText = 'display:block';
	document.getElementById('admin_home').style.cssText = 'display:none';
	document.getElementById('admin_query').style.cssText = 'display:none';
	document.getElementById('admin_category').style.cssText = 'display:none';
	document.getElementById('admin_subcategory').style.cssText = 'display:none';
	document.getElementById('admin_sme').style.cssText = 'display:none';	
	}
function go_admin_category(){
	document.getElementById('admin_category').style.cssText = 'display:block';
	document.getElementById('admin_home').style.cssText = 'display:none';
	document.getElementById('admin_query').style.cssText = 'display:none';
	document.getElementById('admin_subcategory').style.cssText = 'display:none';
	document.getElementById('admin_sme').style.cssText = 'display:none';
	document.getElementById('admin_request').style.cssText = 'display:none';
	}
	/////////////////////////////////////////////////////////////////////////////////////
	function alert_hlo(){
		alert("HLO");
	}
	function fetch_SMEs_name(){
		alert("data to be fetched from backend table");
	}
	function add_new_smes(){
		alert("new smes should be added");
		document.getElementById('details_smes').style.cssText = 'display:none';
		document.getElementById('show_new_input').style.cssText = 'display:block';
	}
	function get_selected_value(){
		alert("sme details to viewed ");
		document.getElementById('details_smes').style.cssText = 'display:block';
		document.getElementById('show_new_input').style.cssText = 'display:none';
	}