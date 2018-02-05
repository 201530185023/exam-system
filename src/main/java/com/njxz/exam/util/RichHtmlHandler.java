package com.njxz.exam.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RichHtmlHandler {
	private Document doc=null;
	private String html;

	private String docSrcParent = "";
	private String docSrcLocationPrex = "";
	private String nextPartId;//����������ɵ������ļ��е�����nextPartId��ͬ   01D395FD.81B8E900

	private String handledDocBodyBlock;
	private List<String> docBase64BlockResults = new ArrayList<String>();
	private List<String> xmlImgRefs = new ArrayList<String>();
	
	
	public RichHtmlHandler(String html) {
		//Jsoup����html���͵��ļ�
		doc = Jsoup.parse(wrappHtml(html));
	}
	
	//�����������ַ�����װ��������html�ļ�
	private String wrappHtml(String html){
		// ��Ϊ���ݹ������ǲ�������doc
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		sb.append(html);

		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
	//��Ҫ����---����������html�ļ�--��Ҫ����ͼƬ
	public void handledHtml(HttpServletRequest request)
			throws IOException {
		Elements imags = doc.getElementsByTag("img");

		if (imags == null || imags.size() == 0) {
			return;
		}

		// ת����word mht ��ʶ��ͼƬ��ǩ���ݣ�ȥ�滻html�е�ͼƬ��ǩ

		for (Element item : imags) {
			// ���ļ�ȡ����
			String src = item.attr("src");
			String srcRealPath = src;
			
			//ͼƬ��ʵ�ʵ�ַ
			String t=request.getSession().getServletContext().getRealPath("");
	        srcRealPath=t.substring(0, t.lastIndexOf('\\'))+src;
	        
	        
			File imageFile = new File(srcRealPath);
			//ͼƬ����
			String imageFielShortName = imageFile.getName();
			//�õ��ļ���׺
			String fileTypeName = srcRealPath.substring(srcRealPath.lastIndexOf(".")+1);

			//���ɴ洢��ftl�ļ��е�ͼƬ����
			String docFileName = "image" + StringUtil.seqGenerate() + "."+ fileTypeName;
			//���ɴ洢��ftl�ļ��е�ͼƬ��ַ--���ݴ˵�ַѰ��ͼƬ��base64����
			String srcLocationShortName = docSrcParent + "/" + docFileName;

			String styleAttr = item.attr("                                                                      style"); // ��ʽ
			//�߶�
			String imagHeightStr=item.attr("height");;
			if(StringUtil.isEmpty(imagHeightStr)){
				imagHeightStr = getStyleAttrValue(styleAttr, "height");
			}
			//���
			String imagWidthStr=item.attr("width");;
			if(StringUtil.isEmpty(imagHeightStr)){
				imagWidthStr = getStyleAttrValue(styleAttr, "width");
			}
	
			imagHeightStr = imagHeightStr.replace("px", "");
			imagWidthStr = imagWidthStr.replace("px", "");
			if(StringUtil.isEmpty(imagHeightStr)){
				//ȥ�õ�Ĭ�ϵ��ļ��߶�
				imagHeightStr="0";
			}
			if(StringUtil.isEmpty(imagWidthStr)){
				imagWidthStr="0";
			}
			int imageHeight = Integer.parseInt(imagHeightStr);
			int imageWidth = Integer.parseInt(imagWidthStr);
			
			// �õ��ļ���word mht��body��
			String handledDocBodyBlock = ImageConverter.toDocBodyBlock(srcRealPath,
					imageFielShortName, imageHeight, imageWidth,styleAttr,
					srcLocationShortName);

			item.parent().append(handledDocBodyBlock);
			item.remove();
			// ȥ�滻ԭ����html�е�imag

			String base64Content = ImageConverter
					.imageToBase64(srcRealPath);
			String contextLoacation = docSrcLocationPrex + "/" + docSrcParent
					+ "/" + docFileName;

			String docBase64BlockResult =ImageConverter
					.generateImageBase64Block(contextLoacation,
							fileTypeName, base64Content,nextPartId);
			docBase64BlockResults.add(docBase64BlockResult);

			String imagXMLHref = "<o:File HRef=3D\"" + docFileName + "\"/>";
			xmlImgRefs.add(imagXMLHref);

		}

	}
	
	//�õ�ͼƬ������--�߶ȣ����
	private String getStyleAttrValue(String style, String attributeKey) {
		if (StringUtil.isEmpty(style)) {
			return "";
		}

		// ��";"�ָ�
		String[] styleAttrValues = style.split(";");
		for (String item : styleAttrValues) {
			// ���� ":"�ָ�
			String[] keyValuePairs = item.split(":");
			if (attributeKey.equals(keyValuePairs[0])) {
				return keyValuePairs[1];
			}
		}

		return "";
	}
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getDocSrcParent() {
		return docSrcParent;
	}

	public void setDocSrcParent(String docSrcParent) {
		this.docSrcParent = docSrcParent;
	}

	public String getDocSrcLocationPrex() {
		return docSrcLocationPrex;
	}

	public void setDocSrcLocationPrex(String docSrcLocationPrex) {
		this.docSrcLocationPrex = docSrcLocationPrex;
	}

	public String getNextPartId() {
		return nextPartId;
	}

	public void setNextPartId(String nextPartId) {
		this.nextPartId = nextPartId;
	}

	public String getHandledDocBodyBlock() {
		String raw= ImageConverter.string2ASCII(doc.getElementsByTag("body").html());
		return raw.replace("=3D", "=").replace("=", "=3D");
	}

	public void setHandledDocBodyBlock(String handledDocBodyBlock) {
		this.handledDocBodyBlock = handledDocBodyBlock;
	}

	public List<String> getDocBase64BlockResults() {
		return docBase64BlockResults;
	}

	public void setDocBase64BlockResults(List<String> docBase64BlockResults) {
		this.docBase64BlockResults = docBase64BlockResults;
	}

	public List<String> getXmlImgRefs() {
		return xmlImgRefs;
	}

	public void setXmlImgRefs(List<String> xmlImgRefs) {
		this.xmlImgRefs = xmlImgRefs;
	}
}
