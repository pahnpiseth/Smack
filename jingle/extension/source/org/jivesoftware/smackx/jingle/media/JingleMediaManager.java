/**
 * $RCSfile$
 * $Revision: $
 * $Date: $11-07-2006
 *
 * Copyright 2003-2006 Jive Software.
 *
 * All rights reserved. Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jivesoftware.smackx.jingle.media;

import org.jivesoftware.smackx.jingle.nat.TransportCandidate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides necessary Jingle Session jmf methods and behavior.
 *
 * The goal of this class is to provide a flexible way to make JingleManager control jmf streaming APIs without implement them.
 * For instance you can implement a file transfer using java sockets or a VOIP Media Manager using JMF.
 * You can implement many JingleMediaManager according to you necessity. 
 *
 * @author Thiago Camargo
 */
public abstract class JingleMediaManager {
    
    private List<PayloadType> payloads = new ArrayList<PayloadType>();

    /**
     * Return all supported Payloads for this Manager
     *
     * @return The Payload List
     */
    public List<PayloadType> getPayloads() {
        return payloads;
    }

    /**
     * Adds a supported Payload type to Manager
     *
     * @param payloadType
     */
    public void addPayloadType(PayloadType payloadType) {
        payloads.add(payloadType);
    }

    /**
     * Removes a supported Payload type from Manager
     *
     * @param payloadType
     */
    public void removePayloadType(PayloadType payloadType) {
        payloads.remove(payloadType);
    }

    /**
     * Get the preferred Payload Type
     */
    public PayloadType getPreferredPayloadType() {
        //TODO a better way to choose the preferred Payload
        return payloads.size() > 0 ? payloads.get(0) : null;
    }

    /**
     * Create a Media Session Implementation
     *
     * @param payloadType
     * @param remote
     * @param local
     * @return
     */
    public abstract JingleMediaSession createMediaSession(PayloadType payloadType, final TransportCandidate remote, final TransportCandidate local);
}