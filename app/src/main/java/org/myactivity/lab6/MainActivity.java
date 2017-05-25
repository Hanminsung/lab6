package org.myactivity.lab6;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private final static String NOTES = "notes.txt"; //file path
    private EditText editT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editT = (EditText)findViewById(R.id.editT);
        Button writeB = (Button)findViewById(R.id.writeB);
        Button clearB = (Button)findViewById(R.id.clearB);
        Button finishB = (Button)findViewById(R.id.finishB);
        Button readB = (Button)findViewById(R.id.readB);

        //make directory & file
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/Myfiles");
        directory.mkdir();
        final File file = new File(directory, NOTES);

        writeB.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                try{
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter out = new OutputStreamWriter(fOut);

                    out.write(editT.getText().toString());
                    out.close();
                }catch(Throwable t){
                    Toast.makeText(getApplicationContext(), "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "Write SD" + NOTES, Toast.LENGTH_SHORT).show();
            }
        });

        clearB.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                editT.setText("");
            }
        });

        finishB.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        readB.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String read = new String();

                try{
                    FileInputStream fIn = new FileInputStream(file);
                    InputStreamReader in = new InputStreamReader(fIn);

                    int res;
                    while((res = in.read()) != -1){
                        read += (char)res;
                    }
                    in.close();

                    editT.setText(read);
                }
                catch(Throwable t){
                    Toast.makeText(getApplicationContext(), "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
