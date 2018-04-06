package com.example.daniyar.kicb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by daniyar on 4/5/18.
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;

    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void renderWindowText(Marker marker, View view){

        String title = marker.getTitle();
        TextView tvTitle = (TextView) view.findViewById(R.id.title_text_view);

        if(!title.equals("")){
            tvTitle.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView tvSnippet = (TextView) view.findViewById(R.id.snippet_text_view);

        if(!snippet.equals("")){
            tvSnippet.setText(snippet);
        }

        ImageView imLogo = (ImageView) view.findViewById(R.id.logo_image_view);
        switch (marker.getTitle()){
            case "KICB":
                imLogo.setImageResource(R.drawable.kicb);
                break;
            case "Aiyl Bank":
                imLogo.setImageResource(R.drawable.aiyl);
                break;
            case "Bank of Asia":
                imLogo.setImageResource(R.drawable.asia);
                break;
            case "DosCredoBank":
                imLogo.setImageResource(R.drawable.dos);
                break;
            case "Bakai Bank":
                imLogo.setImageResource(R.drawable.bakai);
                break;
            case  "BTA Bank":
                imLogo.setImageResource(R.drawable.bta);
                break;
            case "FinansCreditBank":
                imLogo.setImageResource(R.drawable.finans);
                break;
            default:
                imLogo.setImageResource(R.drawable.networklogo);
                break;

        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;
    }
}