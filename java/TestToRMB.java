package test;

public class TestToRMB {

	

	public static void main(String[] args) {
		// System.out.println(toInt("123456"));
		System.out.println(toRMB("100000.123"));
		System.out.println(toRMB("100000.02"));
		System.out.println(toRMB("100001.02"));
		System.out.println(toRMB("1000100.12"));
		System.out.println(toRMB("1000.12"));
		System.out.println(toRMB("1003350.20"));
		System.out.println(toRMB("11003350.20"));
		System.out.println(toRMB("101111003350.20"));
		System.out.println(toRMB("110011003350.20"));
		System.out.println(toRMB("1021111003350.20"));
		System.out.println(toRMB("3350"));
		System.out.println(toRMB("11011003350."));
		System.out.println(toRMB("1101100a350"));
	}

	/**
	 * 将字符串转换为int
	 * 
	 * @param str
	 * @return
	 */
	public static int toInt(String str) {
		// num为转换后的int类型值
		int num = 0;
		if (str != null && !"".equals(str)) {
			boolean isNum = true;// 假设该字符串全是数字
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c < '0' || c > '9') {
					// 验证到字符串包含其他字符
					isNum = false;
					break;
				}
			}
			if (!isNum) {
				throw new RuntimeException("该字符串包含非数字字符，不能转换");
			}
			// 转换字符
			// 例如123456
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				int a = c - '0';
				num += a * Math.pow(10, str.length() - i - 1);
			}
		} else {
			throw new RuntimeException("字符串不能为空");
		}
		return num;
	}

	/**
	 * 将数字转换为人民币的读法,最大单位为亿
	 * 
	 * @param money 
	 * money为String类型，不然大于一千万时会默认变成科学计数法
	 * 包含的小数位最多为两位
	 * @param before 
	 * @return
	 */
	public static String toRMB(String money) {
		System.out.println();
		// 单个数字
		String num[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
		// 小数点前面的单位
		String unit[] = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿","拾","佰","仟"};
		// 小数点后面的单位
		String pointUnit[] = { "分", "角" };
		// 小数点前面部分 整数部分
		String before;
		String after;
		try {
			 before = money.split("\\.")[0];
			// 小数点后面部分 小数部分
			 after = money.split("\\.")[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			// 捕获到异常说明没有小数点
			before = money;
			after = "整";
		}
		
		// 判断零
		boolean isZero = false;
		StringBuilder result = new StringBuilder();
		// 处理小数点前面部分 12345.12
		for (int i = 0; i < before.length(); i++) {
			char c = before.charAt(i);
			int a = c - '0';
			// 把数字添加进去
			if (a != 0) {
				if (isZero) {
					result.append("零");
					isZero = false;
				}
				try {
					result.append(num[a]);
				} catch (ArrayIndexOutOfBoundsException e) {
					//捕获到异常说明格式错误，包含不在num数组可以表示的符号
					System.out.println(money+"格式错误");
					return "转换失败";
				}
				
			} else {
				isZero = true;
			}
			int index = before.length() - i - 1;
			// 把单位添加进去
			if (a != 0 || index == 4 || index == 0|| index == 8)
				try {
					result.append(unit[index]);
				} catch (ArrayIndexOutOfBoundsException e) {
					//捕获到异常说明数据过大，造成单位数组越界
					System.out.println(money+"过大，超过千亿无法转换为大写人民币形式");
					return "转换失败";
				}
			
		}
		// 处理小数点后面部分 12345.12
		if (after == "整") {
			result.append(after);
		} else {
			for (int i = 0; i < after.length(); i++) {
				char c = after.charAt(i);
				int a = c - '0';
				// 把数字添加进去
				result.append(num[a]);
				// 不是0的时候才加上单位
				if (a != 0) {
//					System.out.println(after);//打印小数点部分
					try {
						result.append(pointUnit[after.length() - i - 1]);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println(money+"小数位数过多，无法转换，请控制在两位及以下");
						return "转换失败";
					}
				
				}
			}
		}
		
		System.out.println(money + "转化为大写人民币为：");
		return result.toString();
	}
}
