/*
 * Copyright (C) Max Mu
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License version 2 for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * Please visit www.joshvm.org if you need additional information or
 * have any questions.
 */
package com.josh.node.test.spi.jedi.lcd;

import java.io.IOException;

public interface DisplayDevice {

	public void init(DisplayDeviceDescriptor desc) throws InvalidDeviceDescriptorException, IOException;

	public int getDisplayWidth();

	public int getDisplayHeight();

	public int getColorMode();

	public void update(int top_left_x, int top_left_y, byte[] imageData, int w, int h);
}
