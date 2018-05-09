import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;

import java.io.File;

public class TestEditingHWPFile {

	@Before
	public void setUp()
	{
		File directory = new File(StaticVar.OUTPUT_DIR);
		if (! directory.exists()){
			directory.mkdir();
		}
	}

	@Test
	public void writeTest() throws Exception {
		String inputFile = "test-blank.hwp";

		HWPFile hwpFile = HWPReader.fromFile(StaticVar.SAMPLE_DIR + File.separator + inputFile);

		assertThat(
				hwpFile,
				is(notNullValue())
		);

		String str = "이것은 추가된 문자열입니다.";

		Section s = hwpFile.getBodyText().getSectionList().get(0);
		Paragraph firstParagraph = s.getParagraph(0);
		firstParagraph.getText().addString(str);

		String writePath = StaticVar.OUTPUT_DIR + File.separator + "ed-" + inputFile;
		HWPWriter.toFile(hwpFile, writePath);

		//
		HWPFile edHwpFile = HWPReader.fromFile(writePath);

		Section first  = edHwpFile.getBodyText().getSectionList().get(0);
		firstParagraph = s.getParagraph(0);

		assertThat(
				firstParagraph.getText().getNormalString(0),
				equalTo(str)
		);
	}

}
