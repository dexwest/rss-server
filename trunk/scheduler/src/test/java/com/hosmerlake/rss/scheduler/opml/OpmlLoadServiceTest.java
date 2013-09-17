/**
 * 
 */
package com.hosmerlake.rss.scheduler.opml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hosmerlake.rss.common.content.ParseException;
import com.hosmerlake.rss.scheduler.SchedulerBaseSpringTest;
import com.hosmerlake.rss.scheduler.connection.RssConnection;
import com.hosmerlake.rss.scheduler.model.DefaultObjectStore;
import com.hosmerlake.rss.scheduler.model.ModelException;
import com.hosmerlake.rss.scheduler.rss.RssItems;

/**
 * @author BFOX1
 *
 */
public class OpmlLoadServiceTest extends SchedulerBaseSpringTest<OpmlLoadServiceTest> {

	private static final String INPUT_OBJ_NAME = "opmlLoadServiceTestInput";
	private static final String OUTPUT_OBJ_NAME = "opmlLoadServiceTestOutput.json";
	private static final String CREATE_OBJ_NAME = "opmlLoadServiceTestCreate.json";

	@Autowired
	private OpmlLoadService opmlService;
	@Autowired
	private DefaultObjectStore objStore;

	private ByteArrayInputStream saveIS;
	private String targetTestpath = "target/test-classes/model";
	private String testResourcePath = "src/test/resources/model";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setup() throws Exception 
	{
		try {
			// Create file that can be read
			File inputFile = FileUtils.getFile(testResourcePath, INPUT_OBJ_NAME+".json");
//			FileUtils.openOutputStream(inputFile).close();
			saveIS = new ByteArrayInputStream(FileUtils.readFileToByteArray(inputFile));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCreate() {
		assertNotNull(opmlService);
		
		try {
			Opml opml = createSampleOPML();
			assertNotNull(opml);
			opmlService.saveObj(serializeObj(opml), CREATE_OBJ_NAME);
		} catch (ModelException e) {
			fail();
		}
	}

	private ByteArrayInputStream serializeObj(Opml opml) {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally		
		try {
			File newfile = FileUtils.getFile(targetTestpath, CREATE_OBJ_NAME);
			mapper.writeValue(newfile, opml);
			return new ByteArrayInputStream(FileUtils.readFileToByteArray(newfile));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Opml createSampleOPML() {
		Opml obj = new Opml();
		
		List<RssItems> rssItems = new ArrayList<RssItems>();
		
		RssItems rssItem = new RssItems();
		RssConnection rssCon = new RssConnection();
		rssItem.setConnection(rssCon);
		rssItem.setSchedulerCron("30 * * * *");
		rssItems.add(rssItem );
		obj.setRssItems(rssItems );
		return obj;
	}

	@Test
	public void testLoad() {
		assertNotNull(opmlService);
		
		try {
			Opml opml = (Opml) opmlService.loadObj(INPUT_OBJ_NAME);
			assertNotNull(opml);
		} catch (ModelException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
	}
	@Test
	public void testSave() {
		assertNotNull(opmlService);
		
		try {
			opmlService.saveObj(saveIS, OUTPUT_OBJ_NAME);
			assertTrue(true);
		} catch (ModelException e) {
			fail();
		}
	}
}
