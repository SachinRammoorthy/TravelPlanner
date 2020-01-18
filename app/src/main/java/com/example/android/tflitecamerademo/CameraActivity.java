/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.android.tflitecamerademo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/** Main {@code Activity} class for the Camera app. */
public class CameraActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_camera);
    if (null == savedInstanceState) {
      getFragmentManager()
          .beginTransaction()
          .replace(R.id.container, Camera2BasicFragment.newInstance())
          .commit();
    }

    Toast.makeText(CameraActivity.this, "Please hold the camera steady for 3 seconds", Toast.LENGTH_SHORT).show();

    getResult();

  }

  public void getResult(){
    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        try{
          String aString = Camera2BasicFragment.getFinalAnswer().trim().toLowerCase();
          String finalString = aString.substring(5, aString.indexOf(":"));
          Toast.makeText(CameraActivity.this, finalString, Toast.LENGTH_LONG).show();

          Intent intent = new Intent(CameraActivity.this, InfoActivity.class);
          intent.putExtra("LANDMARK_NAME", finalString);
          startActivity(intent);

        } catch (NullPointerException e){
          Toast.makeText(CameraActivity.this, "Not found", Toast.LENGTH_LONG).show();
          //return "Not found";
        }
      }
    }, 3000);
  }


}
