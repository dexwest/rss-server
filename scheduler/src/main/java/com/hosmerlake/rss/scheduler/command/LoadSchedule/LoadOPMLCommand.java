/**
 * 
 */
package com.hosmerlake.rss.scheduler.command.LoadSchedule;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.chain2.impl.ContextBase;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.command.BaseCommand;
import com.hosmerlake.rss.common.command.Command;
import com.hosmerlake.rss.common.exception.ParseException;
import com.hosmerlake.rss.scheduler.model.ModelException;
import com.hosmerlake.rss.scheduler.opml.Opml;
import com.hosmerlake.rss.scheduler.opml.OpmlLoadService;

/**
 * @author BFOX1
 *
 */
@Component("load-opml-command")
public class LoadOPMLCommand extends BaseCommand implements LoadSchedule {

	private static final Log logger = LogFactory.getLog(LoadOPMLCommand.class);
	
	public static final String CONTEXT_USER_OPML_ID = "user_id";

	@Resource(name="opml-load-service")
	private OpmlLoadService opmlLoadService;
	
	private String status;
	
	private Opml opml;

	@Override 
	public int getOrder() {
		return LoadSchedule.Order.ONE.ordinal();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public boolean run(ContextBase context) {
		try {
			setOpml((Opml) opmlLoadService.loadObj((String) context.get(CONTEXT_USER_OPML_ID)));
			return true;
		} catch (ModelException me) {
			logger.warn("Invalid Model", me);
			this.setStatus("tasked failed: "+ me.toString());
		} catch (ParseException pe) {
			logger.warn("Parse exception", pe);
			this.setStatus("tasked failed: "+ pe.toString());
		} catch (Exception e) {
			logger.error("General exception", e);
			this.setStatus("tasked failed: "+ e.toString());
		} finally {
			if(StringUtils.isEmpty(this.getStatus()))
				this.setStatus("success");
		}
		return false;
	}

	public OpmlLoadService getOpmlLoadService() {
		return opmlLoadService;
	}

	public void setOpmlLoadService(OpmlLoadService opmlLoadService) {
		this.opmlLoadService = opmlLoadService;
	}

	public Opml getOpml() {
		return opml;
	}

	public void setOpml(Opml opml) {
		this.opml = opml;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public List<Command> getDependants() {
		// TODO Auto-generated method stub
		return null;
	}
}
