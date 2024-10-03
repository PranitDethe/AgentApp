const API_URL = 'http://localhost:8080/api';
const userId = sessionStorage.getItem('userId');

if (!userId) {
    window.location.href = 'login.html';
}

document.getElementById('purchaseAgentBtn').addEventListener('click', purchaseAgent);
loadAgents();

function loadAgents() {
    fetch(`${API_URL}/agents/user/${userId}`)
    .then(response => response.json())
    .then(agents => {
        const tbody = document.querySelector('#agentsTable tbody');
        tbody.innerHTML = '';
        agents.forEach(agent => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${agent.agentId}</td>
                <td>${agent.status}</td>
                <td>${agent.supportedBrands || 'Not Configured'}</td>
                <td>
                    <button onclick="configureAgent(${agent.id})" class="btn btn-primary">Configure</button>
                    <button onclick="downloadAgent('${agent.agentId}')" class="btn btn-secondary">Download Agent</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    })
    .catch(error => console.error('Error:', error));
}

function purchaseAgent() {
    fetch(`${API_URL}/agents/purchase/${userId}`, { method: 'POST' })
    .then(response => response.json())
    .then(agent => {
        alert('Agent purchased successfully!');
        loadAgents();
    })
    .catch(error => console.error('Error:', error));
}

function configureAgent(agentId) {
    const brands = prompt('Enter supported brands (comma-separated):');
    if (brands) {
        fetch(`${API_URL}/agents/configure/${agentId}?brands=${encodeURIComponent(brands)}`, {
            method: 'PUT'
        })
        .then(response => response.json())
        .then(agent => {
            alert('Agent configured successfully!');
            loadAgents();
        })
        .catch(error => console.error('Error:', error));
    }
}

function downloadAgent(agentId) {
    // Generate and download the agent script with the agentId embedded
    const scriptContent = `
        const axios = require('axios');
        const agentId = '${agentId}';

        setInterval(() => {
            axios.post('http://localhost:8080/api/agents/status', {
                agentId: agentId,
                status: 'Active'
            }).then(() => {
                console.log('Agent status updated');
            }).catch(err => {
                console.error('Error updating agent status', err);
            });
        }, 60000);
    `;
    const blob = new Blob([scriptContent], { type: 'application/javascript' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `agent-${agentId}.js`;
    link.click();
}
