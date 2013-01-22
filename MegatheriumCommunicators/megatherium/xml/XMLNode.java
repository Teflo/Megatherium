/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SargTeX
 */
public class XMLNode extends Attributable {

	private int level = 0;
	private List<XMLNode> subNodes = new ArrayList<XMLNode>();
	private String content = "";
	private String tagName;

	/**
	 * Initializes the xml node with a tag name.
	 *
	 * @param tagName the name for the xml tag
	 */
	public XMLNode(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the content
	 * @return the object itself for faster programming
	 */
	public XMLNode setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the level
	 * @return the class instance itself for faster programming
	 */
	public XMLNode setLevel(int level) {
		this.level = level;
		return this;
	}

	/**
	 * Returns the tag name of this xml node.
	 *
	 * @return the tag name
	 */
	public String getTagName() {
		return this.tagName;
	}

	/**
	 * Returns an array containing all sub nodes from this node.
	 *
	 * @return the sub nodes
	 */
	public XMLNode[] getSubNodes() {
		return subNodes.toArray(new XMLNode[]{});
	}

	/**
	 * Removes all sub nodes.
	 *
	 * @return the object itself for faster programming
	 */
	public XMLNode clearSubNodes() {
		subNodes.clear();
		return this;
	}

	/**
	 * Appends another node.
	 *
	 * @param node the node
	 * @return the object of this node itself for faster programming
	 */
	public XMLNode append(XMLNode node) {
		this.subNodes.add(node);
		return this;
	}

	/**
	 * Parses the xml node to a string and returns it.
	 *
	 * @return the xml node as a string
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();

		// build prefix
		StringBuilder prefixBuilder = new StringBuilder();
		for (int i = 0; i < level; ++i) {
			prefixBuilder.append("\t");
		}
		String prefix = prefixBuilder.toString();

		// build nodes
		builder.append(prefix).append("<").append(this.tagName).append(this.buildAttributes());

		if (subNodes.size() > 0 || (this.content != null && !this.content.isEmpty())) {
			// close tag
			builder.append(">\n");
			
			if (subNodes.size() > 0) {
				// append sub nodes
				for (XMLNode node : subNodes) {
					node.setLevel(this.level + 1);
					builder.append(node.toString());
				}
			} else {
				// set content
				builder.append(">\n\t").append(prefix).append(this.content).append("\n");
			}
			
			// append closing tag
			builder.append(prefix).append("</").append(this.tagName).append(">\n");
		} else {
			// close tag
			builder.append(" />\n");
		}
		return builder.toString();
	}

	@Override
	public XMLNode set(String name, String value) {
		super.set(name, value);
		return this;
	}
}
