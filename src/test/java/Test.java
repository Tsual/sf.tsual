import sf.uds.util.type.convert.HexConvertor;
import sun.misc.BASE64Encoder;

import java.util.Base64;

public class Test
{
	public static void main(String[] args)
	{
		final String encode_str = new BASE64Encoder().encode(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
		final byte[] decode = Base64.getDecoder().decode(encode_str);
		int a = 0;
	}


}
