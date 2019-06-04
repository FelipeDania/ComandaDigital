package com.example.comandadigital.qrcode;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.comandadigital.R;

public class PrincipalActivity extends AppCompatActivity {

    final int REQUEST_QR_CODE = 10;
    final int REQUEST_BAR_CODE = 11;

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_principal);
    }

    public void scanQRCode(View v){
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, REQUEST_QR_CODE);
        }catch (ActivityNotFoundException anfe){
            Toast.makeText(this, "Baixe o qr code", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            String resultado;
            switch (requestCode){
                case REQUEST_QR_CODE:
                    Toast.makeText(this, "QRCode:\n"+data.getStringExtra("SCAN_RESULT"), Toast.LENGTH_SHORT).show();
                    break;
                case REQUEST_BAR_CODE:
                    Toast.makeText(this, "BarCode:\n"+data.getStringExtra("SCAN_RESULT"), Toast.LENGTH_SHORT).show();
                    break;
            }
        }else{
            Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show();
        }
    }
}
