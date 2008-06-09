/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.rendering.block;

import java.util.List;

import org.xwiki.rendering.listener.Listener;

/**
 * Represents an element of a XWiki Document's content. For example there are Blocks for Paragraphs, Bold parts,
 * Sections, Links, etc. A block has a parent and can have children too for Blocks which are wrapper around other
 * blocks (e.g. Paragraph blocks, List blocks, Bold blocks).
 *
 * @version $Id$
 * @since 1.5M2
 */
public interface Block
{
    /**
     * Let the block send {@link Listener} events corresponding to its content. For example a Paragraph block will
     * send the {@link org.xwiki.rendering.listener.Listener#beginParagraph()} and
     * {@link org.xwiki.rendering.listener.Listener#endParagraph()} events when this method is called.
     *
     * @param listener the listener to which to send the events to.
     */
    void traverse(Listener listener);

    /**
     * Helper method to add a single child block to the current block. For adding several blocks at once use
     * {@link #addChildren(java.util.List)}.
     *
     * @param block the child block to add
     */
    void addChild(Block block);

    /**
     * Adds several children blocks to the current block. For example a bold sentence is made up of a Bold block
     * to which the different words making up the text have been added to.
     *
     * @param blocks the children blocks to add
     */
    void addChildren(List<? extends Block> blocks);

    /**
     * Get the parent block. All blocks have a parent and the top level parent is the {@link XDOM} object.
     * 
     * @return the parent block
     */
    Block getParent();

    /**
     * Sets the parent block.
     *
     * @param parentBlock the parent block
     */
    void setParent(Block parentBlock);

    /**
     * Gets all children blocks.
     *
     * @return the children blocks
     * @see #addChildren(java.util.List) 
     */
    List<Block> getChildren();

    /**
     * Gets the top level Block.
     *
     * @return the top level Block
     */
    Block getRoot();

    /**
     * Gets all the Blocks in the tree which are of the passed Block class.
     *
     * @param blockClass the block class to look for
     * @return all the matching blocks
     */
    <T extends Block> List<T> getChildrenByType(Class<T> blockClass);
}
