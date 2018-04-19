package com.demo.healthy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 多媒体界面
 * 拍照
 */
@ContentView(R.layout.activity_media)
public class MediaActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    @ViewInject(R.id.images)
    RecyclerView recyclerView;

    @ViewInject(R.id.content)
    EditText editText;

    MediaAdapter mediaAdapter;

    Uri photoUri;

    //照片存储路径
    private String path = Environment.getExternalStorageDirectory() + File.separator + "sbw" + File.separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mediaAdapter = new MediaAdapter(this, null);
        recyclerView.setAdapter(mediaAdapter);
    }

    /**
     * 图片的名称
     * @return
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return "IMG_" + dateFormat.format(date);
    }

    /**
     * 拍照
     * @param v
     */
    @Event(R.id.cap)
    private void take(View v) {

        int i = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);    //相机权限申请
        int i1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);//sd卡读写权限
        if (i == PackageManager.PERMISSION_GRANTED && i1 == PackageManager.PERMISSION_GRANTED) {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String fileName = getPhotoFileName() + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                photoUri = Uri.fromFile(new File(path + fileName));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 0);
        }
    }

    /**
     * 上传手账到数据库
     * @param v
     */
    @Event(R.id.save)
    private void upload(View v) {
        String s = editText.getText().toString();
        if (s.equals("")) {
            Toast.makeText(this, "您还没说点啥呢!", Toast.LENGTH_SHORT).show();
            return;
        }
        //创建手账
        ShouZhang shouZhang = new ShouZhang();
        //设置内容
        shouZhang.setContent(s);
        //设置地址
        shouZhang.setUrl(mediaAdapter.toMediaString());
        //设置时间
        shouZhang.setStime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            //保存到数据库
            HgcDb.getInstance().getDb().saveBindingId(shouZhang);
        } catch (DbException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("sz", shouZhang);
        setResult(0, intent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            Uri uri = null;
            if (data != null && data.getData() != null) {
                uri = data.getData();
            }
            if (uri == null) {
                if (photoUri != null) {
                    uri = photoUri;
                    mediaAdapter.add(uri.getPath());
                }
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0) {
            finish();
            return;
        }
        if (grantResults[0] == PackageManager.PERMISSION_DENIED || grantResults[1] != PackageManager.PERMISSION_DENIED) {
            finish();
        }
    }

}
