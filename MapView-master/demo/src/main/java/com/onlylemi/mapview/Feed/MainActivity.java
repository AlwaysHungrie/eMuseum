package com.onlylemi.mapview.Feed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.onlylemi.mapview.Feed.adapter.FeedListAdapter;
import com.onlylemi.mapview.Feed.app.AppController;
import com.onlylemi.mapview.Feed.data.FeedItem;
import com.onlylemi.mapview.MarkLayerTestActivity;
import com.onlylemi.mapview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{
	private static final String TAG = MainActivity.class.getSimpleName();
	private ListView listView;
	private FeedListAdapter listAdapter;
	private List<FeedItem> feedItems;
	private String URL_FEED = "http://dhairyashah.000webhostapp.com/apple.json";
	Button button;
	private Toolbar toolbar;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//toolbar

		//button you created
		//button = (Button)findViewById(R.id.map);

				listView = (ListView) findViewById(R.id.list);

		feedItems = new ArrayList<FeedItem>();

		listAdapter = new FeedListAdapter(this, feedItems);
		listView.setAdapter(listAdapter);
		
		// These two lines not needed,
		// just to get the look of facebook (changing background color & hiding the icon)
		//
		// getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
		//getActionBar().setIcon(
		//		   new ColorDrawable(getResources().getColor(android.R.color.transparent)));

		// We first check for cached request
		Cache cache = AppController.getInstance().getRequestQueue().getCache();
		Entry entry = cache.get(URL_FEED);
		if (entry != null) {
			// fetch the data from cache
			try {
				String data = new String(entry.data, "UTF-8");
				try {
					parseJsonFeed(new JSONObject(data));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		} else {
			// making fresh volley request and getting json
			JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
					URL_FEED, null, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							VolleyLog.d(TAG, "Response: " + response.toString());
							if (response != null) {
								parseJsonFeed(response);
							}
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							VolleyLog.d(TAG, "Error: " + error.getMessage());
						}
					});

			// Adding request to volley request queue
			AppController.getInstance().addToRequestQueue(jsonReq);
		}
		toolbar = (Toolbar) findViewById(R.id.my_toolbar);
		//sets toolbar as the action bar, see last method for further info
		setSupportActionBar(toolbar);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}


	/**
	 * Parsing json reponse and passing the data to feed view list adapter
	 * */
	private void parseJsonFeed(JSONObject response) {
		try {
			JSONArray feedArray = response.getJSONArray("feed");

			for (int i = 0; i < feedArray.length(); i++) {
				JSONObject feedObj = (JSONObject) feedArray.get(i);

				FeedItem item = new FeedItem();
				item.setId(feedObj.getInt("Id"));
				item.setName(feedObj.getString("Event_Name"));
				//Log.d("dha",feedObj.getString("Event_Name"));
				// Image might be null sometimes
				String image = feedObj.isNull("Image") ? null : feedObj
						.getString("Image");
				item.setImge("http://dhairyashah.000webhostapp.com/"+image);
				item.setStatus(feedObj.getString("Description"));
				item.setProfilePic("http://dhairyashah.000webhostapp.com/"+feedObj.getString("Museum_Icon"));
				item.setTimeStamp(feedObj.getString("Date"));

				// url might be null sometimes
				String feedUrl = feedObj.isNull("Link") ? null : feedObj
						.getString("Link");
				item.setUrl(feedUrl);
				if(feedUrl!=null){
					TextView tv = (TextView)findViewById(R.id.txtUrl);
					tv.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							startActivity(new Intent(getApplication(), MarkLayerTestActivity.class));;
						}
					});
				}
				feedItems.add(item);
			}

			// notify data changes to list adapater
			listAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	//this is the switch case where depending on what item you select on
	//the toolbar, it opens corresponding activity
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.map:
				startActivity(new Intent(getApplication(), MarkLayerTestActivity.class));
				return true;
		}
		return true;
	}

}
