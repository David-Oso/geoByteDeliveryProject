import React, { useState } from 'react';

const LocationTable = () => {
  const [locations, setLocations] = useState([
    { id: 1, name: 'Location 1', latitude: '40.7128', longitude: '-74.0060' },
    { id: 2, name: 'Location 2', latitude: '34.0522', longitude: '-118.2437' },
  ]);

  const [newLocation, setNewLocation] = useState({ name: '', latitude: '', longitude: '' });
  const [updateLocation, setUpdateLocation] = useState({ id: null, name: '', latitude: '', longitude: '' });

  const [isAdding, setIsAdding] = useState(false);

  const handleAddLocation = () => {
    if (newLocation.name && newLocation.latitude && newLocation.longitude) {
      setLocations([...locations, { ...newLocation, id: Date.now() }]);
      setNewLocation({ name: '', latitude: '', longitude: '' });
      setIsAdding(false);
    }
  };

  const handleRemoveLocation = (id) => {
    setLocations(locations.filter((location) => location.id !== id));
  };

  const handleUpdateLocation = () => {
    if (updateLocation.id && updateLocation.name && updateLocation.latitude && updateLocation.longitude) {
      setLocations((prevLocations) =>
        prevLocations.map((location) =>
          location.id === updateLocation.id ? { ...updateLocation } : location
        )
      );
      setUpdateLocation({ id: null, name: '', latitude: '', longitude: '' });
    }
  };

  return (
    <div className="container mx-auto mt-8">
      <h1 className="text-2xl font-bold mb-4">Location Table</h1>
      <table className="min-w-full bg-white border border-gray-300">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">S/N</th>
            <th className="py-2 px-4 border-b">Location Name</th>
            <th className="py-2 px-4 border-b">Latitude</th>
            <th className="py-2 px-4 border-b">Longitude</th>
            <th className="py-2 px-4 border-b">Actions</th>
          </tr>
        </thead>
        <tbody>
          {locations.map((location, index) => (
            <tr key={location.id}>
              <td className="py-2 px-4 border-b">{index + 1}</td>
              <td className="py-2 px-4 border-b">{location.name}</td>
              <td className="py-2 px-4 border-b">{location.latitude}</td>
              <td className="py-2 px-4 border-b">{location.longitude}</td>
              <td className="py-2 px-4 border-b">
                <button
                  className="bg-blue-500 text-white px-2 py-1 rounded mr-2"
                  onClick={() => setUpdateLocation(location)}
                >
                  Edit
                </button>
                <button
                  className="bg-red-500 text-white px-2 py-1 rounded"
                  onClick={() => handleRemoveLocation(location.id)}
                >
                  Remove
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="mt-4">
        <button
          className="bg-green-500 text-white px-4 py-2 rounded"
          onClick={() => setIsAdding(true)}
        >
          Add Location
        </button>
      </div>

      {/* Add Location Modal */}
      {isAdding && (
        <div className="fixed inset-0 flex items-center justify-center">
          <div className="bg-gray-800 bg-opacity-75 absolute inset-0"></div>
          <div className="bg-white p-8 rounded z-10">
            <h2 className="text-2xl font-bold mb-4">Add Location</h2>
            <div className="space-y-4">
              <input
                type="text"
                placeholder="Location Name"
                className="p-2 border rounded"
                value={newLocation.name}
                onChange={(e) => setNewLocation({ ...newLocation, name: e.target.value })}
              />
              <input
                type="text"
                placeholder="Latitude"
                className="p-2 border rounded"
                value={newLocation.latitude}
                onChange={(e) => setNewLocation({ ...newLocation, latitude: e.target.value })}
              />
              <input
                type="text"
                placeholder="Longitude"
                className="p-2 border rounded"
                value={newLocation.longitude}
                onChange={(e) => setNewLocation({ ...newLocation, longitude: e.target.value })}
              />
              <div className="flex justify-end">
                <button
                  className="bg-green-500 text-white px-4 py-2 rounded"
                  onClick={handleAddLocation}
                >
                  Add
                </button>
                <button
                  className="bg-gray-500 text-white px-4 py-2 rounded ml-2"
                  onClick={() => setIsAdding(false)}
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Update Location Modal */}
      {updateLocation.id !== null && (
        <div className="fixed inset-0 flex items-center justify-center">
          <div className="bg-gray-800 bg-opacity-75 absolute inset-0"></div>
          <div className="bg-white p-8 rounded z-10">
            <h2 className="text-2xl font-bold mb-4">Update Location</h2>
            <div className="space-y-4">
              <input
                type="text"
                placeholder="Location Name"
                className="p-2 border rounded"
                value={updateLocation.name}
                onChange={(e) => setUpdateLocation({ ...updateLocation, name: e.target.value })}
              />
              <input
                type="text"
                placeholder="Latitude"
                className="p-2 border rounded"
                value={updateLocation.latitude}
                onChange={(e) => setUpdateLocation({ ...updateLocation, latitude: e.target.value })}
              />
              <input
                type="text"
                placeholder="Longitude"
                className="p-2 border rounded"
                value={updateLocation.longitude}
                onChange={(e) => setUpdateLocation({ ...updateLocation, longitude: e.target.value })}
              />
              <div className="flex justify-end">
                <button
                  className="bg-blue-500 text-white px-4 py-2 rounded"
                  onClick={handleUpdateLocation}
                >
                  Update
                </button>
                <button
                  className="bg-gray-500 text-white px-4 py-2 rounded ml-2"
                  onClick={() => setUpdateLocation({ id: null, name: '', latitude: '', longitude: '' })}
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default LocationTable;