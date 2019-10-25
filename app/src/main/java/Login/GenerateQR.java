package Login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import mobileproject.au.edu.sydney.comp5216.mobileproject.R;

public class GenerateQR extends AppCompatActivity {
    Button generate_QRCode;
    Button Close;
    ImageView qrCode;
    EditText mEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_generateqr);
        generate_QRCode = findViewById(R.id.gen_qr);
        qrCode = findViewById(R.id.qrCodeGen);
        Close = findViewById(R.id.close_qr);
        mEditText = findViewById(R.id.editText_genqr);

        generate_QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=mEditText.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrCode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
