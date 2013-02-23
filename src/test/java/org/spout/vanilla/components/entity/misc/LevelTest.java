/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.components.entity.misc;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import org.spout.api.Engine;
import org.spout.api.component.Component;
import org.spout.api.component.ComponentOwner;
import org.spout.api.component.impl.DatatableComponent;
import org.spout.api.entity.Entity;
import org.spout.api.plugin.Platform;

import org.spout.vanilla.EngineFaker;
import org.spout.vanilla.EntityMocker;
import org.spout.vanilla.component.entity.misc.Level;

import static org.junit.Assert.fail;

public class LevelTest {

	static {
		EngineFaker.setupEngine();
	}

	@Test
	public void testLevelComponent() {
		Entity entity = EntityMocker.mockEntity();
		Level levelComponent = entity.add(Level.class);
		levelComponent.setExperience((short) 500);
		if (levelComponent.getExperience() != (short) 500) {
			fail("Wrong experience.");
		}
		if (levelComponent.getLevel() != Level.convertXpToLevel(levelComponent.getExperience())) {
			fail("Wrong level after setting the experience.");
		}

		short level = Level.convertXpToLevel((short) 500);
		short xp = Level.convertLevelToXp(level);
		if (Level.convertXpToLevel(xp) != level) {
			fail("convertXpToLevel giving the wrong level!");
		}

		levelComponent.addExperience(30);
		if (levelComponent.getExperience() != 530) {
			fail("addExperience fail.");
		}

		levelComponent.addExperience(-30);
		if (levelComponent.getExperience() != 500) {
			fail("addExperience negative fail.");
		}
	}
}