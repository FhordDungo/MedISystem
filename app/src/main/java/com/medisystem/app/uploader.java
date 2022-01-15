package com.medisystem.app;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class uploader extends AsyncTask <String, Void,String>{

    AlertDialog dialog;
    Context context;
    public Boolean login = false;
    public uploader(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Upload Status");
    }

    //TODO END PRE EXECUTE

    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        if(s.contains("Save Data Successful"))
        {
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),Dashboard.class);
            context.startActivity(intent_name);
        }
    }

    //TODO END POST EXECUTE
    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String product_id = voids[0];
        String product_name = voids[1];
        String product_generic_name = voids[2];
        String product_manuf = voids[3];
        String product_exp = voids[4];
        String product_bb = voids[5];
        String product_stock = voids[6];

        String login_url = "http://localhost:80/insert.php";

        try {
            URL url = new URL(login_url);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("pd_id","UTF-8")+"="+URLEncoder.encode(product_id,"UTF-8")
                    +"&&"+URLEncoder.encode("pd_name","UTF-8")+"="+URLEncoder.encode(product_name,"UTF-8")
                    +"&&"+URLEncoder.encode("pd_gname","UTF-8")+"="+URLEncoder.encode(product_generic_name,"UTF-8")
                    +"&&"+URLEncoder.encode("pd_manuf","UTF-8")+"="+URLEncoder.encode(product_manuf,"UTF-8")
                    +"&&"+URLEncoder.encode("pd_exp","UTF-8")+"="+URLEncoder.encode(product_exp,"UTF-8")
                    +"&&"+URLEncoder.encode("pd_bb","UTF-8")+"="+URLEncoder.encode(product_bb,"UTF-8")
                    +"&&"+URLEncoder.encode("pd_stock","UTF-8")+"="+URLEncoder.encode(product_stock,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }


        return result;
    }

}
