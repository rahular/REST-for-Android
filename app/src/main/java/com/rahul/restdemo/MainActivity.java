package com.rahul.restdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private EditText etData;
    private Button btnSend;
    private TextView tvResponse, tvResponseCode, tvError;

    private final static String ERROR = "error";
    private final static String RESPONSE = "response";
    private final static String RESPONSE_CODE = "responseCode";

    private String URL = "http://192.168.1.101:3000/api/capitalize";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = (EditText) findViewById(R.id.et_data);
        btnSend = (Button) findViewById(R.id.btn_send);
        tvResponse = (TextView) findViewById(R.id.tv_response);
        tvResponseCode = (TextView) findViewById(R.id.tv_response_code);
        tvError = (TextView) findViewById(R.id.tv_error);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etData.getText().toString();
                String response = null;
                new CallAPI().execute(URL, data);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CallAPI extends AsyncTask<String, Void, HashMap<String, String>> {
        @Override
        protected HashMap<String, String> doInBackground(String... params) {
            RestClient client = new RestClient(params[0]);
            client.AddParam("input", params[1]);

            try {
                client.Execute(RestClient.RequestMethod.POST);
            } catch (Exception e) {
                e.printStackTrace();
            }

            HashMap<String, String> map = new HashMap<String, String>();
            map.put(RESPONSE, client.getResponse());
            map.put(RESPONSE_CODE, client.getResponseCode() + "");
            map.put(ERROR, client.getErrorMessage());

            return map;
        }

        @Override
        protected void onPostExecute(HashMap<String, String> map) {
            tvError.setText(map.get(ERROR));
            tvResponse.setText(map.get(RESPONSE));
            tvResponseCode.setText(map.get(RESPONSE_CODE));
        }
    }
}
