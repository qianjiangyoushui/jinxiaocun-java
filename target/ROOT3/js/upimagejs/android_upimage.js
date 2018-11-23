$('input[type=file]').change(function(e) {
		//压缩图片
		lrz(this.files[0], {width : 800}, function(results) {
			 var _result=results.base64;//压缩后的 
			 var _size=results.base64.size*0.8;
			 
				var image = new Image();
				image.onload = function() {
					EXIF.getData(this, function(){
						var _dataTxt= EXIF.getTag(this, 'DateTime');
						
						var _dataJson = JSON.stringify(EXIF.getAllTags(this));
						
						if(_dataJson.indexOf("ExifIFDPointer")<0||EXIF.getTag(this,'PixelXDimension')*EXIF.getTag(this,'PixelYDimension')<2000000){
							$.alert("只能上传手机拍摄照片！");
							return false;
						}
						
						//判断是否是Iphone拍照 ，普通截图没有ExifIFDPointer参数
						if(typeof(_dataTxt)=='undefined'&&_dataJson.indexOf("ExifIFDPointer")>0){
							var myDate = new Date();
							var year=myDate.getFullYear();
							var month=myDate.getMonth();
							var day=myDate.getDate();
							var hour=myDate.getHours();
							var mint=myDate.getMinutes();
							var secd=myDate.getSeconds();
							_dataTxt=year+":"+month+":"+day+" "+hour+":"+mint+":"+secd;
						}
						demo_report(_result,_size,_dataTxt);
					});
				};
				image.src = results.blob;
				
		});
	});
	
	
	function demo_report( src, size,date) {
	    var Num="";
	    for(var i=0;i<6;i++){
	  	    Num+=Math.floor(Math.random()*10);
	    } 
        var img = new Image();
        var li = document.createElement('li');
        li.id=Num;
        li.setAttribute("class","mui-table-view-cell mui-media mui-col-xs-6");
        (function (Num) {
            li.onclick = function ()
            {
                $("#"+Num).remove();//点击删除图片
                
            }
        })(Num);
        
        img.onload = function () {    
        	var newA=document.createElement("a");
        	newA.id="a"+Num;
        	
        	
            li.appendChild(newA);
            
            var newimg=document.createElement("img");
            newimg.id="img"+Num;
            
			newimg.src=src;
			newA.appendChild(newimg);
			
			var newdiv=document.createElement("div");
			newdiv.setAttribute("class","mui-media-body");
			newdiv.innerHTML=date;
			newA.appendChild(newdiv);
			
            document.querySelector('#images').appendChild(li);
            $("ul img").addClass("mui-media-object");
            
            var newInput = document.createElement("input"); 
//             src=src.replace(",","&");
            newInput.setAttribute("value", src+","+date);
	        newInput.name="files";
	        newInput.id="ip"+Num;
	        newInput.setAttribute("type","text");
	        document.querySelector('#img'+Num).appendChild(newInput);
        };

        img.src = src;
    }