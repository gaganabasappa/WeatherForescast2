// Simulating data retrieval from your backend system
async function fetchWeatherData() {
    // Here you would typically fetch from your backend API
    // For this example, we can use static JSON data for testing purposes
    // const response = await fetch('http://your_backend_api/summary'); // Uncomment this line when backend is ready
    // const data = await response.json();

    // Sample data for demonstration (you can remove this when integrating with your backend)
    const data = [
        { city: "Delhi", avgTemp: 25, maxTemp: 30, minTemp: 20, dominantCondition: "Clear" },
        { city: "Mumbai", avgTemp: 27, maxTemp: 32, minTemp: 22, dominantCondition: "Rain" },
        { city: "Chennai", avgTemp: 29, maxTemp: 34, minTemp: 24, dominantCondition: "Clear" },
        { city: "Bangalore", avgTemp: 22, maxTemp: 27, minTemp: 18, dominantCondition: "Cloudy" },
        { city: "Kolkata", avgTemp: 28, maxTemp: 33, minTemp: 23, dominantCondition: "Clear" },
        { city: "Hyderabad", avgTemp: 24, maxTemp: 29, minTemp: 19, dominantCondition: "Rain" },
    ];
    
    return data; // Return fetched data
}

// Function to update the weather summary table
function updateWeatherSummary() {
    fetchWeatherData().then(data => {
        const summaryBody = document.getElementById('summary-body');
        summaryBody.innerHTML = ""; // Clear existing data

        data.forEach(item => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${item.city}</td>
                <td>${item.avgTemp}</td>
                <td>${item.maxTemp}</td>
                <td>${item.minTemp}</td>
                <td>${item.dominantCondition}</td>
            `;
            summaryBody.appendChild(row);
        });
    });
}

// Function to simulate alerts (replace with actual alert handling logic)
function showAlerts() {
    const alertsList = document.getElementById('alerts-list');
    const alerts = [
        "Alert! Temperature in Mumbai exceeded the threshold.",
        "Alert! Rain expected in Bangalore."
    ];
    
    alerts.forEach(alert => {
        const listItem = document.createElement('li');
        listItem.textContent = alert;
        alertsList.appendChild(listItem);
    });
}

// Initializing the app
function init() {
    updateWeatherSummary();
    showAlerts();
}

// Run the init function when the page loads
window.onload = init;
