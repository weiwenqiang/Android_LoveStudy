package com.example.lovestudy.activity.user;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class HandlePassportActivity extends BaseActivity {

	private TextView txtHandlePassport;
	private StringBuffer data = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_handle_passport);
		setHeadTitle(R.string.how_to_handle_passport);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		txtHandlePassport = (TextView) findViewById(R.id.txt_handle_passport);
	}

	private void bindData() {
		data.append("<һ>����\n");
		data.append("�״����뻤�ձ��뱾�˵��������ڵصĹ����ֳ��뾳�������������ڲ��ӻ�����λפ�صĹ����ֳ��뾳�������������������ѧ�������Ƿ�ɾͽ���ѧУ��������λ���ڵذ������򵱵ع����ֳ��뾳������ѯ����������֮�������ڹ����ֳ��뾳����ͳһ��ġ�\n\n");
		data.append("<��>����/ԭ��\n");
		data.append("1,���֤\n\n");
		data.append("2,2�����������ڲ�ɫ��Ƭ\n\n");
		data.append("<��>����/����\n");
		data.append("1,��ȡ���������Я���������֤����������֤���򻧿ڱ����������ڵ��ɳ����������־ֳ��뾳��������ȡ���й�������˽����������������������\n\n");
		data.append("2,��д������˱�һʽ�ķݣ�����ȫ����д����ÿ�����ݱ���һ�£�Ҫ�ͻ��ڱ��ϵ���Ϣһ�£����֤����Ҫ��д��ʽ���֤�ڵĺ��룬��������ʱ���֤���롣������д����д���ޡ����������Ͻ�ӡ��������(���ű�����һ�µ�)�������μ������ţ��Ա��պ��ѯ��\n\n");
		data.append("3,�ύ���롣�ύ����ʱ����Я���������֤�򻧿ڱ�ԭ���ͻ��ڱ���ҳ����������ҳ�����ҳ�����֤�ĸ�ӡ�����ϣ���д�����������ԭ������׼���÷���Ҫ���2�����������ڲ�ɫ��Ƭ2�š�\n\n");
		data.append("4,�ɷѡ��������ڵݽ���������������֡���˽����������֤�������ִ�����շѴ�����(���������������ս��ѡ�δ��ʱ�޽��ѣ���ȡ֤�����ڽ�����֪ͨ���������һ������δ���ѣ���Ϊ�Զ��������룬������ϲ����˻�����)������ʱ�ޡ����س��뾳�������������������������ǩ�����յ�ʱ���Ǹ��ز�һ��һ����10~15�������ա�\n\n");
		data.append("5,��ȡ���ա���ȡ���տɱ�����ȡ��Ҳ�����˴��죬Ҳ���Կ�����š���������ݸ�������ع涨��������ȡ�������˱����밴�ա���˽����������֤�������ִ����ע����ȡ֤���ڻ���뾳������֪ͨ��ȡ֤���ڰ�ʱ��ȡ֤����ȡ֤���գ������˱���ƾ����˽����������֤�������ִ�����ɷ��վݣ���Я���������֤�򻧿ڲ�������������ĳ��뾳�Ӵ�������ȡ֤������ȡ֤��������ϸ�˶�֤�����ݣ����ֲ����ʱ���������˴��죺������Я������˽����������֤�������ִ�����������֤���������������֤��ӡ�������뾳������ȡ���ա�������ţ�����ѡ�������ţ����ڰ����յ���ƾ����˽����������֤�������ִ�������뾳�����ڵ������ٵݹ�̨�������������ɿ�ݷѡ���ݷ�Χ�Ե��س��뾳�����Ĺ涨Ϊ׼��");
	}

	private void bindView() {
		txtHandlePassport.setText(data);
	}
}
