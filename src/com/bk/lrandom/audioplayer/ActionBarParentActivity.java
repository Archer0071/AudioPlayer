package com.bk.lrandom.audioplayer;

import java.util.Iterator;
import java.util.LinkedHashSet;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.bk.lrandom.audioplayer.fragments.CustomPromptDialogFragment;

public class ActionBarParentActivity extends
		android.support.v7.app.ActionBarActivity {
	private LinkedHashSet<Integer> enableItems = new LinkedHashSet<Integer>();
	private LinkedHashSet<Integer> disableItems = new LinkedHashSet<Integer>();
	private Iterator<Integer> iter;

	public void setEnableItem(LinkedHashSet<Integer> items) {
		this.enableItems = items;
	}

	public void setDisableItem(LinkedHashSet<Integer> items) {
		this.disableItems = items;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		if (!disableItems.isEmpty()) {
			iter = disableItems.iterator();
			while (iter.hasNext()) {
				MenuItem item = menu.findItem(iter.next());
				item.setVisible(false);
			}
		}

		if (!enableItems.isEmpty()) {
			iter = enableItems.iterator();
			while (iter.hasNext()) {
				MenuItem item = menu.findItem(iter.next());
				item.setVisible(true);
			}
		}
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpTo(this, new Intent(this,
					ExplorationActivity.class));
			break;

		case R.id.btn_action_add_playlist:
			CustomPromptDialogFragment newFragment = CustomPromptDialogFragment
					.newInstance();
			newFragment.show(getSupportFragmentManager(), "dialog");
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu
				.findItem(R.id.btn_action_search));
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		return true;
	}

	public void refreshActionBarMenu() {
		this.supportInvalidateOptionsMenu();
	}
	

}
