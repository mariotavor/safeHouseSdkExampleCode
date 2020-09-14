/*
  Copyright 2017 Google Inc. All Rights Reserved.
  <p>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package com.google.android.gms.location.sample.basiclocationsample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.wireguard.android.activity.BaseActivity;
import com.wireguard.android.model.GetRegionResponse;
import com.wireguard.android.model.Tunnel;
import com.wireguard.android.safehouse.SafeHouseConnectionCallback;
import com.wireguard.android.safehouse.SafeHouseGetServersCallBack;

import java.util.ArrayList;


public class MainActivity extends BaseActivity implements SafeHouseConnectionCallback, SafeHouseGetServersCallBack {

    private static final String TAG = MainActivity.class.getSimpleName();
    Button btnConnect;
    OptionsPickerView pvOptions;
    TextView buttonLocationTitle;
    ArrayList<GetRegionResponse.RegionDetail> serverLocations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        serverLocations = new ArrayList<>();
        registerToSafeHouseVpnStatus(this);
        registerToSafeHouseServerStatus(this);
        btnConnect = findViewById(R.id.btnConnect);
        buttonLocationTitle = findViewById(R.id.buttonLocationTitle);
        if (isVPNConnected()) {
            btnConnect.setText("Press to Disconnect");
        } else {
            btnConnect.setText("Press to Connect");
        }

        //get Regions
        getServerRegions();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVPNConnected()) {
                    disconnectVPN();
                } else {
                    if(serverLocations.size()>0)
                        connectVPN();
                }
            }
        });
        ImageView buttonLocation = findViewById(R.id.buttonLocation);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showServersOptionView();
            }
        });
    }

    void showServersOptionView() {
        ArrayList<String> strRegions = new ArrayList();
        try {
            for (int i = 0; i < serverLocations.size(); i++) {
                String jsonRegion = serverLocations.get(i).getName();
                strRegions.add(jsonRegion);
            }
        } catch (Exception e) {

        }
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                try {
                    String name = serverLocations.get(options1).getName();
                    buttonLocationTitle.setText(name);
                    setServerRegion(serverLocations.get(options1).getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })
                .setSubmitText("Done")
                .setCancelText("Cancel")
                .setSubmitColor(Color.rgb(0, 212, 165))
                .setCancelColor(Color.rgb(0, 212, 165))
                .setTitleBgColor(Color.rgb(245, 245, 245))
                .setBgColor(Color.rgb(245, 245, 245))
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(strRegions);
        pvOptions.show();
    }

    @Override
    protected void onSelectedTunnelChanged(@Nullable Tunnel tunnel, @Nullable Tunnel tunnel1) {

    }

    @Override
    public void onVPNConnect() {
        btnConnect.setText("Press to Disconnect");
    }

    @Override
    public void onVPNDisconnect() {
        btnConnect.setText("Press to Connect");
    }

    @Override
    public void onConnectionError(String s) {

    }

    @Override
    public void onGetRegionSuccessfully(ArrayList<GetRegionResponse.RegionDetail> arrayList) {
        setServerRegion(arrayList.get(0).getName());
        serverLocations.addAll(arrayList);
        buttonLocationTitle.setText(arrayList.get(0).getName());
    }

    @Override
    public void onGetRegionError(String s) {

    }
}
