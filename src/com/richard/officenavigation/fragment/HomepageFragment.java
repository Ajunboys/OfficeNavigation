package com.richard.officenavigation.fragment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;

import com.richard.officenavigation.R;

public class HomepageFragment extends BaseFragment implements
		OnItemClickListener, OnClickListener, OnCheckedChangeListener {
	private static final int[] PICS = { R.drawable.pic_miss_liu,
			R.drawable.pic_miss_li, R.drawable.pic_mr_fan,
			R.drawable.pic_mr_luo, R.drawable.pic_mr_yang,
			R.drawable.pic_mr_ma, R.drawable.pic_mr_zhang,
			R.drawable.pic_mr_li, };
	private static final String[] NAMES = { "������ʦ", "������ʦ", "��չԴ��ʦ", "�޸�ǿ��ʦ",
			"�����ʦ", "������ʦ", "��־����ʦ", "�������ʦ", };
	private static final String PIC_KEY = "pic";
	private static final String NAME_KEY = "name";

	private GridView mGvTeachers;

	private Dialog mDlgSelectMethod;
	private Button mBtnDsmConfirm, mBtnDsmCancel;
	private EditText mEditDsmMsg;
	private RadioGroup mRgDsmSelectMethod;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createDialog();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_homepage, container, false);
		mGvTeachers = (GridView) v.findViewById(R.id.container);

		ListAdapter adapter = new SimpleAdapter(getActivity(), getData(),
				R.layout.simple_gradview_item,
				new String[] { PIC_KEY, NAME_KEY }, new int[] { R.id.iv_pic,
						R.id.tv_name });
		mGvTeachers.setAdapter(adapter);
		mGvTeachers.setOnItemClickListener(this);
		return v;
	}

	private void createDialog() {
		//
		mDlgSelectMethod = new Dialog(getActivity());
		mDlgSelectMethod.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.dialog_select_method, null);
		mDlgSelectMethod.setContentView(v);
		mRgDsmSelectMethod = (RadioGroup) v.findViewById(R.id.rg_select_method);
		mEditDsmMsg = (EditText) v.findViewById(R.id.edit_leave_msg);
		mBtnDsmCancel = (Button) v.findViewById(R.id.btn_cancel);
		mBtnDsmConfirm = (Button) v.findViewById(R.id.btn_confirm);
		mBtnDsmConfirm.setOnClickListener(this);
		mBtnDsmCancel.setOnClickListener(this);
		mRgDsmSelectMethod.setOnCheckedChangeListener(this);
	}

	private List<? extends Map<String, ?>> getData() {
		List<Map<String, Object>> data = new LinkedList<Map<String, Object>>();
		Map<String, Object> m = null;
		for (int i = 0; i < PICS.length; i++) {
			m = new HashMap<String, Object>();
			m.put(NAME_KEY, NAMES[i]);
			m.put(PIC_KEY, PICS[i]);
			data.add(m);
		}
		return data;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		d("������" + NAMES[position]);
		mBtnDsmConfirm.setTag(NAMES[position]);
		mDlgSelectMethod.show();
	}

	@Override
	public void onClick(View v) {
		if (v == mBtnDsmConfirm) {
			mDlgSelectMethod.dismiss();
			String name = (String) mBtnDsmConfirm.getTag();
			if (mRgDsmSelectMethod.getCheckedRadioButtonId() == R.id.rb_find_desk) {
				m("Ѱ��" + name + "�칫��");
			} else {
				m("��" + name + "����");
			}
		} else if (v == mBtnDsmCancel) {
			mDlgSelectMethod.dismiss();
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.rb_leave_msg) {
			mEditDsmMsg.setVisibility(View.VISIBLE);
		} else if (checkedId == R.id.rb_find_desk) {
			mEditDsmMsg.setVisibility(View.GONE);
		}
	}

}
