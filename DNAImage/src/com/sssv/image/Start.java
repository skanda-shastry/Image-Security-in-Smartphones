package com.sssv.image;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

class ok
{
ok(int w,int h,int a,int b,int k1)
{
 width=w;
 height=h;
 key1=new int[w*h];
 x=a%w;y=b%h;
key1[0]=k1;

for(int i=1;i<w*h;i++)
{
 key1[i]=(key1[i-1]*83)%21727;
}
for(int i=0;i<w*h;i++){
 key1[i]=key1[i]%8;
}

}


public static int width,height;
public static int[] key1;
int[] x1=new int[8];
int[] y1=new int[8];
int x,y;
int returnpixel(int z1)
{
int r=x,c=y;
int[] flag=new int[8];
for(int i=0;i<8;i++)
{
x1[i]=0;
y1[i]=0;
flag[i]=0;
}

if(!(r+2>width) && !(c+1>height))
{
x1[0]=r+2;
y1[0]=c+1;flag[0]=1;
}

if(!(r-2<=0) && !(c-1<=0))
{
x1[1]=r-2;
y1[1]=c-1;flag[1]=1;
}

if(!(c+2>height) && !(r+1>width))
{
x1[2]=r-2;
y1[2]=c+1;
flag[2]=1;
}

if(!(r-1<=0) && !(c-2<=0))
{
flag[3]=1;
x1[3]=r-1;
y1[3]=c-2;
}

if(!(c-2<=0) && !(r+1>width))
{
flag[4]=1;
x1[4]=r+1;
y1[4]=c-2;
}

if(!(c+2>height) && !(r-1<=0))
{
flag[5]=1;
x1[5]=r-1;
y1[5]=c+2;
}

if(!(c-1<=0) && !(r+2>width))
{
flag[6]=1;
x1[6]=r+2;
y1[6]=c-1;
}

if(!(c+1>height) && !(r-2<=0))
{
flag[7]=1;
x1[7]=r+1;
y1[7]=c+2;
}

int count=0;
for(int i=0;i<8;i++)
{
 if(flag[i]==1)count++;
}


if(count<=2)
{
	if(x<width-1)
	x++;
	if(y<height-1)
	y++;
}
else if(count<=6)
{
	if(x<width-10)x++;
	if(y<height-10)y++;
}
else{
x=x1[key1[z1]];
y=y1[key1[z1]];
}

//System.out.println(count+" "+z1+" "+key1[z1]+" "+width+" "+height+" "+x+" "+y+" "+((y-1)*height)+x);
//System.out.println(z1+" "+x+" "+y+" "+(((y-1)*width)+x));
return ((((y-1)*width)+x));	
}
}

public class Start extends Activity {
	public ImageView imageView;
	Bitmap bitmap=null;
	EditText et1;
	EditText et2;
	
	
	int type=1;
	
	 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button buttonLoadImage = (Button) findViewById(R.id.B1);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                 
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 
                startActivityForResult(i, 1);
            }
        });
        Button captureImage = (Button) findViewById(R.id.B3);
        captureImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(takePictureIntent, 2);
				// TODO Auto-generated method stub
				
			}
		});
      final TextView tv = (TextView) findViewById(R.id.TV2);
      tv.setText("\n"+"Encrypt/Decrypt");
        Button ed = (Button) findViewById(R.id.B2);
        ed.setOnClickListener(new View.OnClickListener() {
        	
			
			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v1) {
				// TODO Auto-generated method stub
				//tv.setText(" "+ "Processing...Please wait for a moment");
				  et1   = (EditText)findViewById(R.id.ET1);
		            et2 = (EditText)findViewById(R.id.ET2);
 
		       
		            String s1=et1.getText().toString();
		            String s2=et2.getText().toString();
		            if(s1.equals("") || s2.equals("") )
		            {
		            	Toast.makeText(getApplicationContext(),
		                        "Please enter the keys",
		                        Toast.LENGTH_LONG).show();
		            	return;
		            }
		            if(bitmap==null)
		            {
		            	Toast.makeText(getApplicationContext(),
		                        "Please select an image",
		                        Toast.LENGTH_LONG).show();
		            	return;
		            }
		            Integer k1 = Integer.parseInt(s1);
		            Integer k2 = Integer.parseInt(s2);
		            
		            
				
	            int w = bitmap.getWidth();
	            int h = bitmap.getHeight();
	            
	            
	          int [] pixels=new int[w*h];
	          bitmap.getPixels(pixels,0,w,0,0,w,h);
	         
	       
	          int no;
	          if(type==1)
	        		no=100;
	        	else if(type==2)
	        		no=50;
	        	else
	        		no=20;  
	          
	            int val;
	        	if(w>h)
	        	 val=w;
	        	else
	        	 val=h;
	        	int size;
	        	if(val<200)
	        		size=val/5;
	        	else
	        		size=val/no;
	        	
	        	
	        	ok chess[]=new ok[size];
	             for(int i=0;i<size;i++)
	        	{
	            		chess[i]=new ok(w,h,i*val/3,i*val/6,k1);
	        	}
	             int[] xor=new int[pixels.length];
	             int[] per=new int[pixels.length];

int [] dna=new int[256];
int[] ar=new int[pixels.length];

for(int i=0;i<256;i++)
{
dna[i]=i;
}
ar[0]=k2;
for(int i=1;i<pixels.length;i++)
{
 ar[i]=(ar[i-1]*97)%22363;
}
for(int i=0;i<pixels.length;i++){
 ar[i]=ar[i]%256;
}

for(int i=0;i<pixels.length;i++){
 xor[i]=pixels[i];
 per[i]=0;
}
int v,count=0;
int alpha,red,green,blue;

for(int j=0;j<pixels.length;j++)
{
	for(int i=0;i<size;i++){
 v=chess[i].returnpixel(j);
 alpha = (pixels[v] >> 24) & 0x000000FF;
 red = ((pixels[v] >> 16) & 0x000000FF)^dna[ar[j]];
 green = ((pixels[v] >> 8) & 0x000000FF)^dna[ar[j]];
 blue = ((pixels[v] ) & 0x000000FF)^dna[ar[j]];
 xor[v]=alpha;
 xor[v]=(xor[v] << 8)+red;
 xor[v]=(xor[v] << 8)+green;
 xor[v]=(xor[v] << 8)+blue;
 if(per[v]==0){per[v]=1;count++;}
	}
}
	           

float res=((float)count/pixels.length)*100;



Bitmap b= Bitmap.createBitmap (w, h, Config.ARGB_8888);
b.setPixels(xor, 0, w, 0, 0, w, h);
File root = Environment.getExternalStorageDirectory();

String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
String filePath = "DNAimage" + timeStamp +".jpg";
File file = new File(root,"/DNA");
if(file.mkdir()==true)
{
	file.mkdir();
}


File editedFile = new File(file,filePath);

FileOutputStream fOut;
try {

	fOut = new FileOutputStream(editedFile);
	b.compress( Bitmap.CompressFormat.PNG, 90, fOut );
	sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	imageView.setImageBitmap(BitmapFactory.decodeFile(root+"/DNA/"+filePath));
	
	tv.setText("\n"+"Encrypted/Decrypted Image");
	
	
	AlertDialog.Builder builder = new AlertDialog.Builder(v1.getContext());
	builder.setTitle("Process Completed successfully ");
   
    builder.setMessage("Encryption/Decryption "+res + "%");
    builder.setPositiveButton("OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT).show();

                }
            });
    builder.show();
    
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


		
				
			}
		});
        
        
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode == RESULT_OK)
        {
        switch(requestCode)
        {
        case 1:
        
    
            Uri selectedImage = data.getData();
            
    
			  try {
				bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
             
            imageView = (ImageView) findViewById(R.id.i1);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
           

       
            
        
        break;
        case 2: 
     
        	Uri selectedImage1 = data.getData();
        	       
    
		  try {
			bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String[] projection = { MediaStore.Images.Media.DATA}; 
          Cursor cursor1 = getContentResolver().query(selectedImage1, projection, null, null, null); 
          int column_index_data = cursor1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); 
          cursor1.moveToFirst(); 
          String capturedImageFilePath = cursor1.getString(column_index_data);
          cursor1.close();
          imageView = (ImageView) findViewById(R.id.i1);
          imageView.setImageBitmap(BitmapFactory.decodeFile(capturedImageFilePath));
          getContentResolver().delete(selectedImage1, null, null);
          
        	break;
        }
        }
    
     
    }
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
       return true; 
    	     
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case R.id.low: type=1;
			break;
		case R.id.medium:type=2;
			break;
		case R.id.high:type=3;
			break;
	}
		return true;
	}
    
}
