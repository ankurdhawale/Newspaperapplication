package c.ankur.expandablelistviewdemo;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

class ExpandableListSetAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> _listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public ExpandableListSetAdapter(MainActivity mainActivity, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
    this.context=mainActivity;
    this._listDataHeader=listDataHeader;
    this.listDataChild=listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertview, ViewGroup viewGroup) {
        final String headerTitle =(String) getGroup(groupPosition);
        if(convertview==null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview=inflater.inflate(R.layout.list_group,null);
        }
        TextView lblListHeader =convertview.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertview;
        //return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean islastchild, View convertview, ViewGroup viewGroup) {
        final String childText =(String) getChild(groupPosition,childPosition);
        if(convertview==null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview=inflater.inflate(R.layout.list_items,null);
        }
        TextView textlistchild=convertview.findViewById(R.id.lblListItem);
        textlistchild.setText(childText);
        return convertview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
