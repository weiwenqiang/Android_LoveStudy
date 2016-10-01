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
		data.append("<一>导读\n");
		data.append("首次申请护照必须本人到户籍所在地的公安局出入境管理处办理，军人在部队或工作单位驻地的公安局出入境管理处办理。关于在外地上学、工作是否可就近在学校、工作单位所在地办理，可向当地公安局出入境管理处咨询办理方法。总之，都是在公安局出入境管理处统一办的。\n\n");
		data.append("<二>工具/原料\n");
		data.append("1,身份证\n\n");
		data.append("2,2寸近期正面免冠彩色照片\n\n");
		data.append("<三>步骤/方法\n");
		data.append("1,领取出境申请表。携带本人身份证或其他户籍证明或户口本到户口所在地派出所、公安分局出入境管理部门领取《中国公民因私出国（境）申请审批表》。\n\n");
		data.append("2,填写申请表。此表一式四份，必须全部填写，且每项内容必须一致，要和户口本上的信息一致；身份证号码要填写正式身份证内的号码，不能填临时身份证号码。否则不填写或填写“无”。表格的右上角印有申请编号(四张表编号是一致的)，必须牢记这个编号，以便日后查询；\n\n");
		data.append("3,提交申请。提交申请时必须携带本人身份证或户口本原件和户口本首页、本人资料页、变更页及身份证的复印件资料，填写完整的申请表原件，并准备好符合要求的2寸近期正面免冠彩色照片2张。\n\n");
		data.append("4,缴费。申请人在递交完申请后须立即持《因私出国（境）证件申请回执》到收费处交费(申请人须在受理当日交费。未按时限交费，领取证件日期将另行通知。若申请后一个月内未交费，视为自动放弃申请，申请材料不再退还本人)。办理时限。当地出入境管理处受理申请后，审批、制作和签发护照的时间是各地不一，一般在10~15个工作日。\n\n");
		data.append("5,领取护照。领取护照可本人领取、也可他人代领，也可以快递上门。具体的内容各地有相关规定。本人领取：申请人本人须按照《因私出国（境）证件申请回执》上注明的取证日期或出入境管理部门通知的取证日期按时领取证件。取证当日，申请人本人凭《因私出国（境）证件申请回执》及缴费收据，并携带居民身份证或户口簿，到受理申请的出入境接待大厅领取证件。领取证件后，请仔细核对证件内容，发现差错，及时改正。他人代领：代领人携带《因私出国（境）证件申请回执》、本人身份证、护照申请人身份证复印件到出入境管理处领取护照。快递上门：若想选择快递上门，须在办理护照当天凭《因私出国（境）证件申请回执》到出入境管理处内的邮政速递柜台办理手续并缴纳快递费。快递范围以当地出入境管理处的规定为准。");
	}

	private void bindView() {
		txtHandlePassport.setText(data);
	}
}
