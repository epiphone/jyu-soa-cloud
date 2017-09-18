window.onload = customize;

function customize(){
	window.document.getElementById('div3').style.display = 'none';
}

function doQuery()
{
    window.document.getElementById('div3').style.display = 'none';
	var q_str = 'tempValue='+document.getElementById('t1').value;
	doAjax('Servlet',q_str,'doQuery_back','post',0);
}

function doQuery_back(result)
{
	if (result.substring(0,5)=='error'){
	   window.document.getElementById('div3').style.display = 'block';
	   window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
   }else{
	   window.document.getElementById('t2').value=""+result;
   }
}

