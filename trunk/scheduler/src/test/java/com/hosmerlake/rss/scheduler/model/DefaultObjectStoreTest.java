package com.hosmerlake.rss.scheduler.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hosmerlake.rss.scheduler.SchedulerBaseSpringTest;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {DefaultObjectStoreTest.SpringConfig.class})
public class DefaultObjectStoreTest extends AbstractJUnit4SpringContextTests {

	private static final String INPUT_OBJ_NAME = "DefaultObjectStoreTestLoad";
	private static final String OUTPUT_OBJ_NAME = "opmlLoadServiceTestCreate.json";
	
	private ByteArrayInputStream saveFileOS;
	
	@Autowired
	private DefaultObjectStore objStore;
	@Before
	public void setup() 
	{
		try {
			File input = FileUtils.getFile(objStore.getObjStorePath(), INPUT_OBJ_NAME);
			FileUtils.openOutputStream(input).close();
			File output = FileUtils.getFile(objStore.getObjStorePath(), OUTPUT_OBJ_NAME);
			saveFileOS = new ByteArrayInputStream(FileUtils.readFileToByteArray(output));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Configuration
	@Import( SchedulerBaseSpringTest.SpringConfig.class )
	public static class SpringConfig {
    }
	
	@Test
	public void loadTest() {
		assertNotNull(objStore);
		try {
			assertNotNull(objStore.loadObj(INPUT_OBJ_NAME));
		} catch (ModelException e) {
			fail();
		}
	}

	@Test
	public void saveTest() {
		assertNotNull(objStore);
		try {
			objStore.saveObj(saveFileOS,INPUT_OBJ_NAME);
			assertTrue(true);
		} catch (ModelException e) {
			fail();
		}
	}
}
